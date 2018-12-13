package yogi.report.viewer.compare;

import java.util.List;

import yogi.report.compare.template.BaseCompareReportTemplate;
import yogi.report.compare.template.CompareHeaderValuesGenerator;
import yogi.tools.swingx.treetable.TreeTableDataHelper;

public class CompareReportTreeTableDataHelper<T> implements TreeTableDataHelper<CompareReportData<T>> {
	private BaseCompareReportTemplate<T> reportTemplate;
	private List<List<String>> headerValues;
	private String separator = "";
	public CompareReportTreeTableDataHelper(BaseCompareReportTemplate<T> reportTemplate) {
		super();
		this.reportTemplate = reportTemplate;
		headerValues = new CompareHeaderValuesGenerator<T>(reportTemplate, false, false).getHeaderValues();
	}

	public Object getValueAt(CompareReportData<T> reportData, int column) {
		if(column ==0) return " ";
		int reportColumnIndex = column -1;
		return reportData.getValue(reportTemplate, reportColumnIndex);
	}

	public int getColumnCount() {
		return headerValues.get(0).size()+1;
	}

	public boolean isEditable(CompareReportData<T> reportData, int column) {
		return false;
	}

	public void setValueAt(CompareReportData<T> reportData, Object aValue, int column) {
	}

	public String getColumnName(int column) {
		if(column == 0) return "Tree";
		StringBuilder rtnValue = new StringBuilder();
		rtnValue.append(headerValues.get(0).get(column -1));
		for(int i =1; i < headerValues.size(); i++)
		{
			rtnValue.append(separator);
			rtnValue.append(headerValues.get(i).get(column -1));
		}
		return rtnValue.toString();
	}

}
