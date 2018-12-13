package yogi.server.gui.report.io.db.converter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

import yogi.base.util.JsonAssistant;
import yogi.base.util.logging.Logging;
import yogi.server.gui.record.converter.Converter;

public class ReportQueriesConverter implements Converter {

	private static Logger logger = Logging.getLogger(ReportQueriesConverter.class);
	
	public static String Query = "query";
	public static String Columns = "columns";
	public static String Filters = "filters";
	public static String GroupFilters = "groupFilters";	
	public static String Name = "name";
	public static String ColumnName = "columnName";			
	public static String OldColumnName = "";
	public static String NewColumnName = "";

	@SuppressWarnings("unchecked")
	@Override
	public String convert(String string) {
		boolean convertFlag = false;
		
		if(string == null || string.isEmpty()) {
			logger.warning("Empty Data");
			logger.info("Record convert : " + convertFlag);
			return string;
		}
		
		LinkedHashMap<String, Object> jsonObject = (LinkedHashMap<String, Object>)JsonAssistant.get().fromJson(string, Object.class);
		if(jsonObject == null || jsonObject.isEmpty()) {
			logger.warning("Empty Data object");
			logger.info("Record convert : " + convertFlag);
			return string;
		}
		
		LinkedHashMap<String, Object> queryMap = (LinkedHashMap<String, Object>) jsonObject.get(Query);
		if(queryMap == null || queryMap.isEmpty()) {
			logger.warning("Empty or no Query");
			logger.info("Record convert : " + convertFlag);
			return string;
		}		
		
		ArrayList<LinkedHashMap<String, Object>> columnsList = (ArrayList<LinkedHashMap<String, Object>>)queryMap.get(Columns);
		if(columnsList == null || columnsList.isEmpty()) {
			logger.warning("Empty or no Columns");
			logger.info("Record convert : " + convertFlag);
			return string;
		}
		
		ArrayList<LinkedHashMap<String, Object>> filtersList = (ArrayList<LinkedHashMap<String, Object>>)queryMap.get(Filters);
		if(filtersList == null || filtersList.isEmpty()) {
			logger.warning("Empty or no Filters");
		}
		
		ArrayList<LinkedHashMap<String, Object>> groupFiltersList = (ArrayList<LinkedHashMap<String, Object>>)queryMap.get(GroupFilters);
		if(groupFiltersList == null || groupFiltersList.isEmpty()) {
			logger.warning("Empty or no Group Filters");
		}
		
		for(LinkedHashMap<String, Object> column : columnsList) {
			if(changeNames(column, Name)) convertFlag = true;
		}
		
		for(LinkedHashMap<String, Object> filter : filtersList) {	
			if(changeNames(filter, ColumnName)) convertFlag = true;
		}
		
		for(LinkedHashMap<String, Object> groupFilter : groupFiltersList) {	
			if(changeNames(groupFilter, ColumnName)) convertFlag = true;
		}
		
		if(!convertFlag) {
			logger.warning("No Conversion needed");
			logger.info("Record convert : " + convertFlag);
			return string;
		}
		logger.info("Record convert : " + convertFlag);
		queryMap.put(Columns, columnsList);
		queryMap.put(Filters, filtersList);
		queryMap.put(GroupFilters, groupFiltersList);		
		jsonObject.put(Query, queryMap);
		String rtnVal = new String(JsonAssistant.get().toJson(jsonObject));
		rtnVal = rtnVal.replaceAll("\":0.0", "\":0");
		rtnVal = rtnVal.replaceAll("\":1.0", "\":1");
		rtnVal = rtnVal.replaceAll("\":-1.0", "\":-1");
		return rtnVal;
	}

	private boolean changeNames(LinkedHashMap<String, Object> dataMap, String fieldType) {
		String fieldName = (String) dataMap.get(fieldType);
		if(fieldName != null && fieldName.equalsIgnoreCase(OldColumnName)) {			
			dataMap.put(fieldType, fieldName.replace(OldColumnName, NewColumnName));
			return true;
		}
		return false;
	}

}
