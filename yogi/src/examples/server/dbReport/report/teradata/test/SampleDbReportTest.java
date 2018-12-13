package examples.server.dbReport.report.teradata.test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import yogi.base.app.testing.TestErrorReporter;
import yogi.base.io.db.DBException;
import yogi.base.io.db.QueryExecutor;
import yogi.base.io.resource.db.teradata.TeradataDbResource;
import yogi.base.util.JsonAssistant;
import yogi.report.server.Column;
import yogi.report.server.Filter;
import yogi.report.server.Query;
import yogi.report.server.ReportData;
import yogi.report.server.ReportServerImpl;
import yogi.report.server.ReportTableData;
import yogi.report.server.config.ReportConfigProvider;

import examples.server.odservice.io.ODServiceReportReader;
import junit.framework.TestCase;

public class SampleDbReportTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		TestErrorReporter.end();
	}
	public void testTeradatadb(){
		ReportConfigProvider.ReportDirectory = "*/examples/server/dbReport/report/teradata/test/ReportConfig.properties";
		ODServiceReportReader.DataLocation="./src/examples/server/dbReport/report/teradata/test/";
		yogi.base.io.resource.db.teradata.TeradataDbResource.teraJdbcUrl="jdbc:teradata://edtdpcop2.corpaa.aa.com";
		yogi.base.io.resource.db.teradata.TeradataDbResource.accountName="MOSMCROFCSTAPP02";
		yogi.base.io.resource.db.teradata.TeradataDbResource.passd="Microfor2015";
		TeradataDbResource tdbr = new TeradataDbResource();
		String q="select  STN_CD SCD , STN_EXP_DT , LAT_DEGREE_QTY, LAT_MINUTE_QTY, LAT_SECOND_QTY,LAT_HEMSPHR_CD,  LNGTD_DEGREE_QTY, LNGTD_MINUTE_QTY, LNGTD_SECOND_QTY, LNGTD_HEMSPHR_CD  from PROD_REFERENCE_DATA_VWS.INDSTRY_STATION where stn_cd ='DFW'";
		q = "select TABLENAME,COLUMNID, COLUMNNAME  FROM DBC.COLUMNS  where DATABASENAME = 'PROD_REFERENCE_DATA_VWS' AND TABLENAME = 'INDSTRY_STATION'";
		try {
			List<Object[]> rows = QueryExecutor.get().executeQuery(tdbr, q, 100);
			System.out.println(rows.get(0));
		} catch (DBException e) {
			e.printStackTrace();
		}

	}
	public void testTeradata(){
		ReportConfigProvider.ReportDirectory = "*/examples/server/dbReport/report/teradata/test/ReportConfig.properties";
		ODServiceReportReader.DataLocation="./src/examples/server/dbReport/report/teradata/test/";
		yogi.base.io.resource.db.teradata.TeradataDbResource.teraJdbcUrl="jdbc:teradata://edtdpcop2.corpaa.aa.com";
		yogi.base.io.resource.db.teradata.TeradataDbResource.accountName="MOSMCROFCSTAPP02";
		yogi.base.io.resource.db.teradata.TeradataDbResource.passd="Microfor2015";

		ODServiceReportReader.numOfThreads=3;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("STN_CD", "In", "DFW", ""));
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("STN_CD", "STN_CD", "", false, 0, 0));		
		columns.add(new Column("STN_EXP_DT", "STN_EXP_DT", "", false, 0, 0));
		columns.add(new Column("LAT_DEGREE_QTY", "LAT_DEGREE_QTY", "", false, 0, 0));
		columns.add(new Column("LAT_MINUTE_QTY", "LAT_MINUTE_QTY", "", false, 0, 0));
		columns.add(new Column("LAT_SECOND_QTY", "LAT_SECOND_QTY", "", false, 0, 0));
		columns.add(new Column("LAT_HEMSPHR_CD", "LAT_HEMSPHR_CD", "", false, 0, 0));
		
		Query query = new Query("DbReport", new String[]{"Dummy"}, "SampleTeraDataReport", columns, filters, groupFilters);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}
}
