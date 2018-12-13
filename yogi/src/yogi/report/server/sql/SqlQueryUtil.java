package yogi.report.server.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import yogi.base.indexing.ManyIndexer;
import yogi.report.condition.Condition;
import yogi.report.server.Column;
import yogi.report.server.Filter;
import yogi.report.server.Query;
import yogi.report.server.config.ReportConfigProvider;

public class SqlQueryUtil {

	public static String buildSqlQuery(Query query){
		List<Column> selectedColumns = query.getSelectedColumns();
		StringBuilder queryStringBuilder = new StringBuilder(); 
		StringBuilder groupStringBuilder = new StringBuilder(); 
		StringBuilder orderbyStringBuilder = new StringBuilder(); 
		ReportConfigProvider<Object> reportConfigProvider = new ReportConfigProvider<Object>(query.getReportName());		 
		queryStringBuilder.append("select ");
		groupStringBuilder.append(" group by ");
		orderbyStringBuilder.append(" order by ");
		boolean groupByFlag=false;
		boolean orderByFlag=false;
		StringBuilder errorMessage = new StringBuilder();
		for(int i=0;i<selectedColumns.size();i++){
			if(selectedColumns.get(i).isGroupBy()){
				groupByFlag = true;break;
			}
		}		
		for(int i=0;i<selectedColumns.size();i++){
			
			 String name = selectedColumns.get(i).getName();
			 if (name.equals("count")){
				 if(groupByFlag){
					 queryStringBuilder.append(" count(*) ");
				 }else{
					 throw new RuntimeException("count column should only be selected when doing group reports.");
				 }
			 }else{
				 
				if(selectedColumns.get(i).isGroupBy()){
					groupStringBuilder.append(name);
					 groupStringBuilder.append(",");	
				 }
				 
				if(groupByFlag && !selectedColumns.get(i).isGroupBy()){
					 String groupByFunction = selectedColumns.get(i).getFunction().trim();
					 if(groupByFunction.isEmpty()){
						 if(errorMessage.length() ==0) errorMessage.append("Select Group Function for ");
						 errorMessage.append( name + ",");
					 }else{
						 try{
							 groupByFunction = SqlFunctions.valueOf(groupByFunction).getDescription();
							 queryStringBuilder.append(groupByFunction);
							 queryStringBuilder.append("(");	
							 queryStringBuilder.append(name);
							 queryStringBuilder.append(")");	
						 } catch (Throwable e) {
							 errorMessage.append("Function not valid :" + groupByFunction + " for Column :" + name + ",");
						 }
					 }
				 }else{
					 queryStringBuilder.append(name);
				 }
			 }
			 if(i!=selectedColumns.size()-1){
				 queryStringBuilder.append(",");	
			 }
			 
			 //Order By
			 if(selectedColumns.get(i).getSortOrder()==1){
				 orderByFlag = true;
				 orderbyStringBuilder.append(name).append(" asc ");
				 orderbyStringBuilder.append(",");	
			 }else if(selectedColumns.get(i).getSortOrder()==-1){
				 orderByFlag = true;
				 orderbyStringBuilder.append(name).append(" desc ");
				 orderbyStringBuilder.append(",");	
			 }
			
		}
		queryStringBuilder.append(" from TABLENAME ");
		List<Filter> myFilters = query.getValidFilters();
		if(!myFilters.isEmpty()){
			queryStringBuilder.append("where ");
		}
		ManyIndexer<String, Filter> columnFiltersMap = new ManyIndexer<String, Filter>();
		for(Filter filter: myFilters)
		{
			// validate filters
			if(!(filter.getColumnName().isEmpty() || filter.getValue().isEmpty())){
				columnFiltersMap.index(filter.getColumnName(), filter);
			}
		}

		List<Entry<String, List<Filter>>> entries = new ArrayList<Entry<String, List<Filter>>>(columnFiltersMap.entrySet());
		for(int k = 0; k < entries.size(); k++){
			List<Filter> filters = entries.get(k).getValue();
			
			if(filters.size() > 1){
				queryStringBuilder.append(" ( ");
			}

			for(int i = 0; i < filters.size(); i++){
				Filter filter = filters.get(i); 
				Condition<Object> filterCondition = reportConfigProvider.getCondition(filter);
				queryStringBuilder.append(filterCondition.getSqlValue(filter.getColumnName()));

				if(i!=filters.size()-1){
					queryStringBuilder.append(" or ");
				}
			}
			if(filters.size() > 1){
				queryStringBuilder.append(" ) ");
			}
			if(k!=entries.size()-1){
				queryStringBuilder.append(" and ");
			}
		}
		if(groupByFlag){
			queryStringBuilder.append(groupStringBuilder.subSequence(0, groupStringBuilder.length()-1));
		}
		if(orderByFlag){
			queryStringBuilder.append(orderbyStringBuilder.subSequence(0, orderbyStringBuilder.length()-1));
		}
		
		if(errorMessage.length() != 0) {
			
			if(errorMessage.charAt(errorMessage.length()-1)==',') throw new RuntimeException(errorMessage.substring(0, errorMessage.length()-1));		
			throw new RuntimeException(errorMessage.toString());
		}
		
		return queryStringBuilder.toString();
		
	}
}
