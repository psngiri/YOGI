package yogi.report.server;

import java.util.List;

import yogi.report.server.config.ReportConfig;

public interface ReportServer {
	
	List<String> getReports();
	ReportConfig<?> getReportConfiguration(String ReportName);
	ReportTableData generateReport(Query query, int queryId, String userId);
	ReportDataRows expandGroup(int queryId, int groupIndex, List<ReportDataIndex> indexes, String userId);
	boolean closeReport(int queryId, String userId);
	boolean closeReports();
	
}
