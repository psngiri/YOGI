package yogi.report.presentation;

import yogi.report.column.ColumnDefinition;


public class ReportListObject<T> {
	private ColumnDefinition<T> column;
	
	public ReportListObject(ColumnDefinition<T> column) {
		super();
		this.column = column;
	}

	public ColumnDefinition<T> getColumn() {
		return column;
	}

	@Override
	public String toString() {
		return column.getName();
	}
}
