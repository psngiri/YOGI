package yogi.paging.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class ColumnNameConfig {
	private LinkedHashSet<String> requiredColumns;
	private Map<String, Integer> reportColumns;
	private List<Integer> columnIndexes;
	
	public ColumnNameConfig(String... columns) {
		requiredColumns = new LinkedHashSet<String>();
		if(columns.length > 0){
		    for (String column : columns){
			requiredColumns.add(column);
		    }
		}
		reportColumns = new HashMap<String, Integer>();
		columnIndexes = new ArrayList<Integer>();
	}
	
	public int getSize(){
		return requiredColumns.size();
	}
	
	public void processColumn(String columnName, int index){
	    	reportColumns.put(columnName, index);
	}	

	public List<Integer> getColumnIndexes(){
	    return columnIndexes;
	}
	
	public Integer getColumnIndex(String name){
		return reportColumns.get(name);
	}
	
	public void validateColumns() {
		List<String> missingColumns = new ArrayList<String>();		
		for(String name : requiredColumns){
		    Integer value = reportColumns.get(name);
		    if(value == null){
			missingColumns.add(name);
		    }else{
			columnIndexes.add(value);
		    }
		}
		if(!missingColumns.isEmpty())
		{
			throw new RuntimeException("These are the Mandatory Columns : " + requiredColumns + ", Missing Columns : " + missingColumns);
		}
	}
}
