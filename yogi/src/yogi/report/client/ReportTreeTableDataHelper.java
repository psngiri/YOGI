package yogi.report.client;

import java.util.List;

import yogi.report.server.ReportData;
import yogi.tools.swingx.treetable.TreeTableDataHelper;

public class ReportTreeTableDataHelper implements TreeTableDataHelper<ReportData> {
	private List<String> columnNames;
	public ReportTreeTableDataHelper(List<String> columnNames) {
		super();
		this.columnNames = columnNames;
	}

	public Object getValueAt(ReportData reportData, int column) {
		if(column ==0) return "";
		return reportData.getValue(column-1);
	}

	public int getColumnCount() {
		return columnNames.size()+1;
	}

	public boolean isEditable(ReportData reportData, int column) {
		return false;
	}

	public void setValueAt(ReportData reportData, Object aValue, int column) {
	}

	public String getColumnName(int column) {
		if(column == 0) return "Tree";
		return columnNames.get(column -1);
	}

}
