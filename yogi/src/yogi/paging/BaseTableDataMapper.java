package yogi.paging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yogi.paging.changes.ColumnChange;
import yogi.paging.column.TableColumnConfig;

public abstract class BaseTableDataMapper<T, I> implements TableDataMapper<T, I> {

	private static final long serialVersionUID = 4729583488565867142L;
	
	private int columnCount;	
	private List<TableColumnConfig<?>> tableColumnConfigs;
	private List<TableActionConfig> tableActionConfigs;
	private Map<String,Integer> columnIndices;
	protected int defaultFindColumnIndex = -1;
	private boolean defaultEditSelectAllValue;
	private I input;
	protected HashMap<String, String> columnDisplayNames;
	
	public BaseTableDataMapper(List<TableColumnConfig<?>> tableColumnConfigs, List<TableActionConfig> tableActionConfigs, String defaultFindColumnName, boolean defaultEditSelectAllValue, I input) {
		super();
		this.columnCount = tableColumnConfigs.size();
		this.tableColumnConfigs = tableColumnConfigs;
		this.columnIndices = new HashMap<String, Integer>(columnCount);
		for(int i=0;i<tableColumnConfigs.size();i++){
			String columnName = tableColumnConfigs.get(i).getName();
			columnIndices.put(columnName, i);
			if(defaultFindColumnIndex == -1 && defaultFindColumnName != null && !defaultFindColumnName.isEmpty()) {
				setDefaultColumnIndex(defaultFindColumnName, i, columnName);				
			}
		}
		this.tableActionConfigs = tableActionConfigs;
		this.defaultEditSelectAllValue = defaultEditSelectAllValue;
		this.input = input;		
	}
	
	@Override
	public HashMap<String, String> getColumnDisplayNames() {
		return columnDisplayNames;
	}

	public I getInput() {
		return input;
	}

	private void setDefaultColumnIndex(String defaultColumnName, int i,	String columnName) {
		if(defaultColumnName.equals(columnName))
		{
			this.defaultFindColumnIndex = i;
		}
	}	
	
	protected void setDefaultFindColumnIndex(int defaultFindColumnIndex) {
		this.defaultFindColumnIndex = defaultFindColumnIndex;
	}

	public boolean isDefaultEditSelectAllValue() {
		return defaultEditSelectAllValue;
	}
	

	@SuppressWarnings("unchecked")
	protected  <K> K reduce(Object object)
	{
	    return (K) object;
	}

	@Override
	public int getColumnCount() {
		return columnCount;
	}
	
	@Override
	public Object convertColumnValue(int columnHeaderIndex, String value) {
		Object columnValue = null;
		if(value == null || value.length() <= 0) return columnValue;
		try {
			columnValue = tableColumnConfigs.get(columnHeaderIndex).scan(value);
		} catch(Exception e) {
			throw new RuntimeException("Not a valid value : " + value);
		}
		return columnValue;
	}

	@Override
	public String convertColumn(int columnHeaderIndex, Object value) {
		String rtnVal = "";
		if(value == null) return rtnVal;
		rtnVal = tableColumnConfigs.get(columnHeaderIndex).format(value);
		return rtnVal;
	}
	
	@Override
	public String getModifiedColumn(int columnHeaderIndex, Object value, String modifyFactor) {		
		return tableColumnConfigs.get(columnHeaderIndex).getModifiedColumnValue(value, modifyFactor);
	}
	
	@Override
	public List<ColumnChange> getModifiedValuesForDependentColumn(int rowDataIndex, int columnHeaderIndex, String oldValue, String newValue, TableData<T> tableData) {		
		return null;
	}
	
	@Override
	public int compare(int columnHeaderIndex, Object object1, Object object2) {
		if(object1 == null && object2 == null) {
			return 0;
		} else if(object1 == null && object2 != null) {
			return -1;
		} else if(object1 != null && object2 == null) {
			return 1;
		}
		return tableColumnConfigs.get(columnHeaderIndex).compare(object1, object2);		
	}
	
	@Override
	public TableColumnConfig<?> getColumnConfig(int columnHeaderIndex) {		
		return tableColumnConfigs.get(columnHeaderIndex);
	}

	public List<TableColumnConfig<?>> getTableColumnConfigs() {
		return tableColumnConfigs;
	}
	
	public Integer getColumnIndex(String columnName) {
		return columnIndices.get(columnName);		
	}
	
	public int getDefaultFindColumnIndex() {
		return defaultFindColumnIndex;
	}

	public List<TableActionConfig> getTableActionConfigs() {
		return tableActionConfigs;
	}

	@Override
	public boolean isChangeAllowed(int rowDataIndex, int columnHeaderIndex, TableData<T> tableData) {
	     return true;
	}
	
	public void addTableColumnConfigs(TableColumnConfig<?> tableColumnConfig){
		this.tableColumnConfigs.add(tableColumnConfig);
		this.columnCount++;
	}
}
