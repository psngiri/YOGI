package yogi.report.viewer.compare;

import yogi.report.compare.CompareGroup;
import yogi.report.compare.template.CompareReportTemplate;
import yogi.report.viewer.ReportGroupData;

public interface CompareReportData<T> extends ReportGroupData<T>{
	Object getValue(CompareReportTemplate<T> reportTemplate, int column);
	CompareGroup<T> getCompareGroup();
}
