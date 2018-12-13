package examples.server.dbReport.report;

import yogi.base.io.resource.db.SimpleDbResource;
import yogi.report.server.row.jdbc.BaseDbReportConfig;


public class SampleDbReportConfig extends BaseDbReportConfig 
{
	private static final long serialVersionUID = 1L;

	public SampleDbReportConfig()
	{
		this("DbReport", "SampleDbReport", "ANALYST");
	}
	
	public SampleDbReportConfig(String reportType, String reportName, String tableName)
	{
		super(reportType, reportName, tableName, new SimpleDbResource());
	}
	
	public SampleDbReportConfig(String reportType, String reportName, String tableName, String jdbcUrl)
	{
		super(reportType, reportName, tableName, new SimpleDbResource(jdbcUrl));
	}
}