package yogi.paging.command;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import yogi.paging.TableActionConfig;
import yogi.paging.column.TableColumnConfig;

public class TableConfig implements Serializable {
	
	private static final long serialVersionUID = 5674862547860953291L;
	private TableColumnConfig<?>[] tableColumnConfigs;
	protected List<TableActionConfig> tableActionConfigs;
	protected int defaultFindColumnIndex;
	private boolean defaultEditSelectAllValue;
	private HashMap<String, String> columnDisplayNames;
	
	public TableConfig(TableColumnConfig<?>[] tableColumnConfigs,List<TableActionConfig> tableActionConfigs, int columnIndex, boolean defaultEditSelectAllValue, HashMap<String, String> columnDisplayNames) {
		super();
		this.tableColumnConfigs = tableColumnConfigs;
		this.tableActionConfigs = tableActionConfigs;
		this.defaultFindColumnIndex = columnIndex;
		this.defaultEditSelectAllValue = defaultEditSelectAllValue;
		this.columnDisplayNames = columnDisplayNames;
	}
	
	public TableConfig() {
		super();
	}

	public TableColumnConfig<?>[] getTableColumnConfigs() {
		return tableColumnConfigs;
	}
	public List<TableActionConfig> getTableActionConfigs() {
		return tableActionConfigs;
	}
	
	public int getDefaultFindColumnIndex() {
		return this.defaultFindColumnIndex;
	}
	
	public boolean isDefaultEditSelectAllValue() {
		return defaultEditSelectAllValue;
	}

	public HashMap<String, String> getColumnDisplayNames() {
		return columnDisplayNames;
	}
	
}
