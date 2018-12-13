package examples.server.dbReport.report.teradata;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import yogi.base.io.resource.db.DbResource;
import yogi.base.io.resource.db.teradata.TeradataDbResource;
import yogi.report.server.row.jdbc.BaseDbReportConfig;


public class SampleDbReportConfig extends BaseDbReportConfig
{
	private static final long serialVersionUID = 1L;

	public SampleDbReportConfig()
	{
//		super("DbReport", "SampleTeraDataReport", "PROD_INDDATA_VW.tru_od_smr", new TeradataDbResource());
		this("DbReport", "SampleTeraDataReport", "PROD_REFERENCE_DATA_VWS.INDSTRY_STATION ");
	}
	
	public SampleDbReportConfig(String reportType, String reportName, String tableName)
	{
		super(reportType, reportName, tableName, new TeradataDbResource());
	}
	
	public SampleDbReportConfig(String reportType, String reportName, String tableName, String jdbcUrl)
	{
		super(reportType, reportName, tableName, new TeradataDbResource(jdbcUrl));
	}

//	protected void createColumns(String tableName, DbResource dbResource) {
//		try {
//			String[] split = tableName.split("\\.");
//			int length = split.length;
//			String tableNamePattern = split[length-1];
//			String schemaName = null;
//			if(length > 1) schemaName = split[length-2];
//			String query = "select COLUMNNAME  FROM DBC.COLUMNS  where DATABASENAME = '"+schemaName+"' AND TABLENAME = '"+tableNamePattern+"'";
//			PreparedStatement pstmt = dbResource.getConnection().prepareStatement(query);
//			ResultSet columns = pstmt.executeQuery();
//
//			while(columns.next()){
//				String columnName = columns.getString("COLUMNNAME").trim();
//				System.out.println(columnName);
//				int dataType = java.sql.Types.VARCHAR/*columns.getInt("DATATYPE")*/;
//				if(columnName.endsWith("_dt") || columnName.endsWith("_DT"))
//					dataType=java.sql.Types.DATE;
//				int columnSize = 10/*columns.getInt("COLUMNSIZE")*/;
//				int decimalDegits = 0/*columns.getInt("DECIMALDIGITS")*/;
//				switch(dataType){
//				case java.sql.Types.VARCHAR:
//				case java.sql.Types.CHAR:
//					createStringCustomColumnConfig(columnName, columnSize);
//					break;
//				case java.sql.Types.DOUBLE:
//				case java.sql.Types.FLOAT:
//					createDoubleCustomColumnConfig(columnName, columnSize, decimalDegits);
//					break;
//				case java.sql.Types.BIGINT:
//				case java.sql.Types.INTEGER:
//				case java.sql.Types.SMALLINT:
//				case java.sql.Types.DECIMAL:
//				case java.sql.Types.REAL:
//					createLongCustomColumnConfig(columnName, columnSize);
//					break;
//				case java.sql.Types.TIMESTAMP:
//					createTimestampCustomColumnConfig(columnName, columnSize);
//					break;
//				case java.sql.Types.DATE:
//					createDateDbCustomColumnConfig(columnName, columnSize);
//					break;
//				default:
//					System.out.println(columnName + ":"+ dataType);
//					createStringCustomColumnConfig(columnName, columnSize);					
//				}
//				createLongCustomColumnConfig("count", 10);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

}