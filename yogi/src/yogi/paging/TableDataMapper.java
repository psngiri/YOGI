package yogi.paging;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import yogi.paging.changes.ColumnChange;
import yogi.paging.column.TableColumnConfig;

public interface TableDataMapper<T, I> extends Serializable{
	int getColumnCount();
	Object getColumnValue(int columnHeaderIndex, T rowData);
	Object convertColumnValue(int columnHeaderIndex, String value);
	String convertColumn(int columnHeaderIndex, Object value);
	int compare(int columnHeaderIndex, Object object1, Object object2);
	TableColumnConfig<?> getColumnConfig(int columnHeaderIndex);
	List<TableActionConfig> getTableActionConfigs();
	Integer getColumnIndex(String columnName);
	String getModifiedColumn(int columnHeaderIndex, Object value, String modifyFactor);
	List<ColumnChange> getModifiedValuesForDependentColumn(int rowDataIndex, int columnHeaderIndex, String oldValue, String newValue, TableData<T> tableData);
	boolean isChangeAllowed(int rowDataIndex, int columnHeaderIndex, TableData<T> tableData);
	int getDefaultFindColumnIndex();
	I getInput();
	boolean isDefaultEditSelectAllValue();
	HashMap<String, String> getColumnDisplayNames();
}
