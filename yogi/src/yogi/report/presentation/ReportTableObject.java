package yogi.report.presentation;

import yogi.report.column.ColumnDefinition;


public class ReportTableObject<T> {
	private ColumnDefinition<T> column;
	private int sortIndex = -1;
	private boolean sortAssending = false;
	
	public ReportTableObject(ColumnDefinition<T> column) {
		super();
		this.column = column;
	}

	public ColumnDefinition<T> getColumn() {
		return column;
	}

	public boolean isSortAssending() {
		return sortAssending;
	}

	public void setSortAssending(boolean sortAssending) {
		this.sortAssending = sortAssending;
	}

	public int getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}

}
