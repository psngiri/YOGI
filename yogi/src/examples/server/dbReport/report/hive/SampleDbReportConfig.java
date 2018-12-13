package examples.server.dbReport.report.hive;

import yogi.base.io.resource.db.hive.HiveDbResource;
import yogi.report.server.row.jdbc.BaseDbReportConfig;


public class SampleDbReportConfig extends BaseDbReportConfig
{
	private static final long serialVersionUID = 1L;

	public SampleDbReportConfig()
	{
		this("DbReport", "SampleDbReport", "custprofile");
	}
	
	public SampleDbReportConfig(String reportType, String reportName, String tableName)
	{
		super(reportType, reportName, tableName, new HiveDbResource());
	}
	
	public SampleDbReportConfig(String reportType, String reportName, String tableName, String jdbcUrl)
	{
		super(reportType, reportName, tableName, new HiveDbResource(jdbcUrl));
	}

}