package yogi.report.client.model;

import yogi.report.server.config.ColumnConfig;
import yogi.report.server.config.FunctionConfig;

public class SelectedColumnsTableRow {
	private ColumnConfig<?,?> columnConfig;
	private FunctionConfig<?> functionConfig;
	private boolean  groupBy;
	private int sortOrder;
	private int groupSortOrder;
	public SelectedColumnsTableRow(ColumnConfig<?, ?> columnConfig) {
		super();
		this.columnConfig = columnConfig;
	}
	public ColumnConfig<?, ?> getColumnConfig() {
		return columnConfig;
	}
	public FunctionConfig<?> getFunctionConfig() {
		return functionConfig;
	}
	public boolean isGroupBy() {
		return groupBy;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public int getGroupSortOrder() {
		return groupSortOrder;
	}
	void setColumnConfig(ColumnConfig<?, ?> columnConfig) {
		this.columnConfig = columnConfig;
	}
	void setFunctionConfig(FunctionConfig<?> functionConfig) {
		this.functionConfig = functionConfig;
	}
	void setGroupBy(boolean groupBy) {
		this.groupBy = groupBy;
	}
	void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	void setGroupSortOrder(int groupSortOrder) {
		this.groupSortOrder = groupSortOrder;
	}
	
}