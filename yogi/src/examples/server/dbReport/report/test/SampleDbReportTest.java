package examples.server.dbReport.report.test;

import java.util.ArrayList;
import java.util.List;

import yogi.base.app.testing.TestErrorReporter;
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
	public void testAnalyst(){
		ReportConfigProvider.ReportDirectory = "*/examples/server/dbReport/report/test/ReportConfig.properties";
		ODServiceReportReader.DataLocation="./src/examples/server/dbReport/report/test/";
		yogi.base.io.resource.db.SimpleDbResource.JDBCUrl="jdbc:oracle:thin:IFS_APP/Pls_reset_pass987@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=rmodat01-scan.qcorpaa.aa.com)(PORT=1521))(CONNECT_DATA=(SERVER=dedicated)(SERVICE_NAME=IFSTST_SVC.QCORPAA.AA.COM )))";

		ODServiceReportReader.numOfThreads=3;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("ANL_FST_NM", "Like", "%K%", ""));
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("ANL_EMP_ID", "ANL_EMP_ID", "", false, 0, 0));		
		columns.add(new Column("ANL_FST_NM", "ANL_FST_NM", "", false, 0, 0));
		columns.add(new Column("ANL_LST_NM", "ANL_LST_NM", "", false, 0, 0));
		columns.add(new Column("ANL_MDL_NM", "ANL_MDL_NM", "", false, 0, 0));
		
		Query query = new Query("DbReport", new String[]{"Analyst1"}, "SampleDbReport", columns, filters, groupFilters);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}
	
	public void testCount(){
		ReportConfigProvider.ReportDirectory = "*/examples/server/dbReport/report/test/ReportConfig.properties";
		ODServiceReportReader.DataLocation="./src/examples/server/dbReport/report/test/";
		yogi.base.io.resource.db.SimpleDbResource.JDBCUrl="jdbc:oracle:thin:IFS_APP/Pls_reset_pass987@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=rmodat01-scan.qcorpaa.aa.com)(PORT=1521))(CONNECT_DATA=(SERVER=dedicated)(SERVICE_NAME=IFSTST_SVC.QCORPAA.AA.COM )))";

		ODServiceReportReader.numOfThreads=3;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("count", "count", "", false, 0, 0));
		columns.add(new Column("ANL_EMP_ID", "ANL_EMP_ID", "", true, 0, 0));		
		
		Query query = new Query("DbReport", new String[]{"Analyst1"}, "SampleDbReport", columns, filters, groupFilters);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}

}
