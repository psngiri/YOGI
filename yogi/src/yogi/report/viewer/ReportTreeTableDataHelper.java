package yogi.report.viewer;

import java.util.List;

import yogi.report.column.ColumnDefinition;
import yogi.tools.swingx.treetable.TreeTableDataHelper;

public class ReportTreeTableDataHelper<T> implements TreeTableDataHelper<ReportData<T>> {
	private List<ColumnDefinition<T>> columns;
	public ReportTreeTableDataHelper(List<ColumnDefinition<T>> columns) {
		super();
		this.columns = columns;
	}

	public Object getValueAt(ReportData<T> reportData, int column) {
		if(column ==0) return "";
		ColumnDefinition<T> columnDefinition = columns.get(column-1);
		return reportData.getValue(columnDefinition);
	}

	public int getColumnCount() {
		return columns.size()+1;
	}

	public boolean isEditable(ReportData<T> reportData, int column) {
		return false;
	}

	public void setValueAt(ReportData<T> reportData, Object aValue, int column) {
	}

	public String getColumnName(int column) {
		if(column == 0) return "Tree";
		return columns.get(column -1).getName();
	}

}
