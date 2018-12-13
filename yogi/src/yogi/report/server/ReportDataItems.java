package yogi.report.server;

import java.util.List;

import yogi.report.LineWriter;
import yogi.report.compare.template.BaseCompareReportTemplate;
import yogi.report.compare.template.TemplateCompareGroupFormatter;
import yogi.report.template.TemplateGroupFormatter;

public interface ReportDataItems<R>{
	LineWriter generateReport(Query query, BaseCompareReportTemplate<R> simpleReportTemplate, TemplateGroupFormatter<R> templateGroupFormatter, TemplateCompareGroupFormatter<R> templateCompareGroupFormatter, LineWriter lineWriter);
	ReportTableData generateReport(Query query);
	ReportDataRows expandGroup(int groupIndex, List<ReportDataIndex> indexes);
	public int getQueryId();
	
}
