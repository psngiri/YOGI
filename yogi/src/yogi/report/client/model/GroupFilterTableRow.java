package yogi.report.client.model;

import yogi.report.server.config.ColumnConfig;
import yogi.report.server.config.ConditionConfig;

public class GroupFilterTableRow {
	private ColumnConfig<?,?> columnConfig;
	private ConditionConfig<?> conditionConfig;
	private String value;
	private  ColumnConfig<?,?> marketLevelColumnConfig;
	public GroupFilterTableRow(ColumnConfig<?, ?> columnConfig) {
		super();
		this.columnConfig = columnConfig;
	}
	public ColumnConfig<?, ?> getColumnConfig() {
		return columnConfig;
	}
	public ConditionConfig<?> getConditionConfig() {
		return conditionConfig;
	}
	public String getValue() {
		return value;
	}
	public ColumnConfig<?, ?> getMarketLevelColumnConfig() {
		return marketLevelColumnConfig;
	}
	void setColumnConfig(ColumnConfig<?, ?> columnConfig) {
		this.columnConfig = columnConfig;
	}
	void setConditionConfig(ConditionConfig<?> conditionConfig) {
		this.conditionConfig = conditionConfig;
	}
	void setValue(String value) {
		this.value = value;
	}
	void setMarketLevelColumnConfig(ColumnConfig<?, ?> marketLevelColumnConfig) {
		this.marketLevelColumnConfig = marketLevelColumnConfig;
	}
	
}