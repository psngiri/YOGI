package yogi.report.server.row.jdbc;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Types.*;
import yogi.base.io.Formatter;
import yogi.base.io.resource.db.DbResource;
import yogi.paging.column.formatter.DoubleFormatter;
import yogi.paging.column.types.DateDbTableColumnConfig;
import yogi.paging.column.types.DoubleTableColumnConfig;
import yogi.paging.column.types.LongTableColumnConfig;
import yogi.paging.column.types.TimeDbTableColumnConfig;
import yogi.paging.column.types.TimestampTableColumnConfig;
import yogi.report.condition.dateDb.DateDbFormatter;
import yogi.report.condition.dateDb.DateDbSqlFormatter;
import yogi.report.condition.timeDb.TimeDbFormatter;
import yogi.report.condition.timestamp.TimestampFormatter;
import yogi.report.condition.timestamp.TimestampSqlFormatter;
import yogi.report.server.config.BaseValidator;
import yogi.report.server.config.ReportConfig;
import yogi.report.server.config.column.DateDbColumnConfig;
import yogi.report.server.config.column.DoubleColumnConfig;
import yogi.report.server.config.column.LongColumnConfig;
import yogi.report.server.config.column.StringColumnConfig;
import yogi.report.server.config.column.TimeDbColumnConfig;
import yogi.report.server.config.column.TimestampColumnConfig;
import yogi.report.server.row.Row;
import yogi.report.server.row.config.RowReportDataItems;
import yogi.report.server.row.evaluators.RowDateDbEvaluator;
import yogi.report.server.row.evaluators.RowDoubleEvaluator;
import yogi.report.server.row.evaluators.RowLongEvaluator;
import yogi.report.server.row.evaluators.RowStringEvaluator;
import yogi.report.server.row.evaluators.RowTimeDbEvaluator;
import yogi.report.server.row.evaluators.RowTimestampEvaluator;


public abstract class BaseDbReportConfig extends ReportConfig<Row> 
{
	private static final long serialVersionUID = 1L;

	public BaseDbReportConfig(String reportType, String reportName, String tableName, DbResource dbResource)
	{
		super(reportType, reportName, new RowReportDataItems(new JDBCRowExecutor(dbResource, tableName), false),new BaseValidator(".", "Enter Dummy"));
		//this.setDataSetCommand(DummyDataSetsCommand.class);
		this.setDataSetLabelName("Select Dummy"); 
		createColumns(tableName, dbResource);
		
	}

	protected void createColumns(String tableName, DbResource dbResource) {
		try {
			DatabaseMetaData metaData = dbResource.getConnection().getMetaData();
			String tableNamePattern = null;
			String schemaPattern = null;
			String catalog = null;
			String[] split = tableName.split("\\.");
			if(split.length > 0) tableNamePattern = split[split.length -1];
			if(split.length > 1) schemaPattern = split[split.length -2];
			if(split.length > 2) catalog = split[split.length -3];
			ResultSet columns = metaData.getColumns(catalog, schemaPattern, tableNamePattern, null);
			while(columns.next()){
				String columnName = columns.getString("COLUMN_NAME");
				int dataType = columns.getInt("DATA_TYPE");
				int columnSize = columns.getInt("COLUMN_SIZE");
				int decimalDegits = columns.getInt("DECIMAL_DIGITS");
				System.out.println(columnName + ":"+ dataType+ ":"+ columnSize+ ":"+ decimalDegits);
				switch(dataType){
				case VARCHAR:
				case CHAR:
					createStringCustomColumnConfig(columnName, columnSize);
					break;
				case DOUBLE:
				case FLOAT:
					createDoubleCustomColumnConfig(columnName, columnSize, decimalDegits);
					break;
				case BIGINT:
				case TINYINT:
				case INTEGER:
				case SMALLINT:
				case DECIMAL:
				case REAL:
					createLongCustomColumnConfig(columnName, columnSize);
					break;
				case TIMESTAMP:
					createTimestampCustomColumnConfig(columnName, columnSize);
					break;
				case TIME:
					createTimeDbCustomColumnConfig(columnName, columnSize);
					break;
				case DATE:
					createDateDbCustomColumnConfig(columnName, columnSize);
					break;
				default:
					System.out.println(columnName + ":"+ dataType);
					createStringCustomColumnConfig(columnName, columnSize);					
				}
			}
			createLongCustomColumnConfig("count", 10);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void createStringCustomColumnConfig(String key, int size) {
		this.addColumn(new StringColumnConfig<Row>(key,new RowStringEvaluator(key)));
	}
	protected void createTimestampCustomColumnConfig(String key, int size) {
		TimestampFormatter formatter = new TimestampFormatter();
		this.addColumn(new TimestampColumnConfig<Row>(key,new RowTimestampEvaluator(key), formatter, new TimestampTableColumnConfig(key, 130, false, formatter), false, getTimestampSqlFormatter()));
	}
	protected void createTimeDbCustomColumnConfig(String key, int size) {
		TimeDbFormatter formatter = new TimeDbFormatter();
		this.addColumn(new TimeDbColumnConfig<Row>(key,new RowTimeDbEvaluator(key), formatter, new TimeDbTableColumnConfig(key, 100, false, formatter), false, getTimeDbSqlFormatter()));
	}
	protected void createDateDbCustomColumnConfig(String key, int size) {
		DateDbFormatter formatter = new DateDbFormatter();
		this.addColumn(new DateDbColumnConfig<Row>(key,new RowDateDbEvaluator(key), formatter, new DateDbTableColumnConfig(key, 100, false, formatter), false, getDateDbSqlFormatter()));
	}
	protected void createLongCustomColumnConfig(String key, int size) {
		this.addColumn(new LongColumnConfig<Row>(key,new RowLongEvaluator(key),null,new LongTableColumnConfig(key, 120, false),false));
	}
	protected void createDoubleCustomColumnConfig(String key, int size, int decimal) {
		Formatter<Double> formatter = new DoubleFormatter(size, decimal);
		this.addColumn(new DoubleColumnConfig<Row>(key,new RowDoubleEvaluator(key),formatter,new DoubleTableColumnConfig(key, 120, false),false));
	}
	protected Formatter<Long> getTimestampSqlFormatter() {
		return new TimestampSqlFormatter();
	}
	protected Formatter<Long> getDateDbSqlFormatter() {
		return new DateDbSqlFormatter();
	}
	protected Formatter<Long> getTimeDbSqlFormatter() {
		return new TimeDbFormatter();
	}
}