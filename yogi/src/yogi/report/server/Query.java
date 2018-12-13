package yogi.report.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;

public class Query  implements Serializable{

	private static final long serialVersionUID = 6003861206821066755L;
	private String serverType;
	private String[] object;
	private String reportName;
	private List<Column> columns;
	private List<Filter> filters;
	private List<Filter> groupFilters;
	private boolean singleGrouping;
	private boolean groupReport;
	private CompoundFilter compoundFilter;

	
	public Query(String serverType,String[] object, String reportName,List<Column> columns, List<Filter> filters,List<Filter> groupFilters) {
		this(serverType, object,  reportName, columns,  filters, groupFilters,null);
	}
	
	public Query(String serverType,String[] object, String reportName,List<Column> columns, List<Filter> filters,List<Filter> groupFilters, CompoundFilter compoundFilter) {
		this(serverType, object,  reportName, columns,  filters, groupFilters,false,false);
		this.compoundFilter = compoundFilter;
	}
	
	public Query(String serverType, String[] object, String reportName, List<Column> columns, List<Filter> filters,
			List<Filter> groupFilters, boolean singleGrouping, boolean groupReport, CompoundFilter compoundFilter) {
		this(serverType, object,  reportName, columns,  filters, groupFilters,singleGrouping,groupReport);
		this.compoundFilter = compoundFilter;
	}

	public Query(String serverType,String[] object, String reportName,List<Column> columns, List<Filter> filters,List<Filter> groupFilters, boolean singleGrouping, boolean groupReport) {
		super();
		this.serverType=serverType;
		this.object = object;
		this.reportName = reportName;
		this.columns = columns;
		this.filters = filters;
		this.groupFilters = groupFilters;
		this.singleGrouping = singleGrouping;
		this.groupReport = groupReport;
	}

	public String getServerType() {
		return serverType;
	}

	public String[] getObject() {
		return object;
	}

	public String getReportName() {
		return reportName;
	}

	public List<Column> getSelectedColumns() {
		return columns;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public List<Filter> getValidFilters() {
		List<Filter> rtnValue = new ArrayList<Filter>(filters.size());
		for(Filter filter: getFilters())
		{
			// validate filters
			if(!(filter.getColumnName().isEmpty() || (filter.getValue().isEmpty() && !(filter.getCondition().equals(IsNullConditionConfig.BLANK) || filter.getCondition().equals(IsNotNullConditionConfig.NOT_BLANK))))){
				rtnValue.add(filter);
			}
		}	
		return rtnValue;
	}

	public List<Filter> getGroupFilters() {
		return groupFilters;
	}

	public String toString() {
		return "Query [object=" + object + ", reportName=" + reportName + ", columns=" + columns
				+ ", filters=" + filters + ", groupFilters=" + groupFilters
				+ "]";
	}

	public boolean isGroupReport() {
		if(!groupReport) return false;
		return isGroupBy();
	}

	public boolean isGroupBy() {
		for(Column column: columns){
			if(column.isGroupBy())return true;
		}
		return false;
	}

	public boolean isSingleGrouping() {
		return singleGrouping;
	}

	public CompoundFilter getCompoundFilter() {
		return compoundFilter;
	}
	
}
