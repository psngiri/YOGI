package yogi.report.server.row.jdbc.teradata;

import yogi.base.io.Formatter;
import yogi.base.io.resource.db.teradata.TeradataDbResource;
import yogi.paging.column.types.DateDbTableColumnConfig;
import yogi.paging.column.types.TimestampTableColumnConfig;
import yogi.report.condition.dateDb.DateDbFormatter;
import yogi.report.condition.dateDb.DateDbSqlFormatter;
import yogi.report.condition.timestamp.TimestampFormatter;
import yogi.report.condition.timestamp.TimestampSqlFormatter;
import yogi.report.server.config.column.DateDbColumnConfig;
import yogi.report.server.config.column.TimestampColumnConfig;
import yogi.report.server.row.Row;
import yogi.report.server.row.evaluators.RowDateDbEvaluator;
import yogi.report.server.row.evaluators.RowTimestampEvaluator;
import yogi.report.server.row.jdbc.BaseDbReportConfig;


public class TeradataDbReportConfig extends BaseDbReportConfig
{
	private static final long serialVersionUID = 1L;
	
	public TeradataDbReportConfig(String reportType, String reportName, String tableName)
	{
		super(reportType, reportName, tableName, new TeradataDbResource());
	}
	
	public TeradataDbReportConfig(String reportType, String reportName, String tableName, String jdbcUrl)
	{
		super(reportType, reportName, tableName, new TeradataDbResource(jdbcUrl));
	}
	
	@Override
	protected void createDateDbCustomColumnConfig(String key, int size) {
		DateDbFormatter formatter = new DateDbFormatter();
		this.addColumn(new DateDbColumnConfig<Row>(key,new RowDateDbEvaluator(key), formatter, new DateDbTableColumnConfig(key, 100, false, formatter), false, new DateDbSqlFormatter()));
	}
	
	protected void createTimestampCustomColumnConfig(String key, int size) {
		TimestampFormatter formatter = new TimestampFormatter();
		this.addColumn(new TimestampColumnConfig<Row>(key,new RowTimestampEvaluator(key), formatter, new TimestampTableColumnConfig(key, 130, false, formatter), false, new TimestampSqlFormatter()));
	}

	@Override
	protected Formatter<Long> getTimestampSqlFormatter() {
		return new TimestampSqlFormatter();
	}
	
	@Override
	protected Formatter<Long> getDateDbSqlFormatter() {
		return new DateDbSqlFormatter();
	}

}