package examples.server.dbReport.report.hive.test;

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

//import examples.server.dbReport.report.command.DummyDataSetsCommand;
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

	public void testHive(){
		ReportConfigProvider.ReportDirectory = "*/examples/server/dbReport/report/hive/test/ReportConfig.properties";
		ODServiceReportReader.DataLocation="./src/examples/server/dbReport/report/hive/test/";
		yogi.base.io.resource.db.hive.HiveDbResource.JDBCUrl="jdbc:hive2://bdhcld01.hdq.aa.com:10000/cuscore";

		ODServiceReportReader.numOfThreads=3;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("enroll_dt", "In", "13/oct/2014, 14/oct/2014", ""));
		filters.add(new Filter("age","LessThan","5",""));
	//	filters.add(new Filter("start_dt", "GreaterThan", "1-jan-2012", ""));
		List<Filter> groupFilters = new ArrayList<Filter>();
	//	columns.add(new Column("pe_ml_bal", "pe_ml_bal", "", false, 0, 0));
		columns.add(new Column("start_dt", "start_dt", "", false, 0, 0));
	//	columns.add(new Column("loyalty_score", "loyalty_score", "", false, 0, 0));
	//	columns.add(new Column("top1_accrue_cat", "top1_accrue_cat", "", false, 0, 0));
		columns.add(new Column("enroll_dt", "enroll_dt", "", false, 0, 0));
		columns.add(new Column("gender", "gender", "", false, 0, 0));
		columns.add(new Column("age", "age", "", false, 0, 0));
		
		Query query = new Query("DbReport", new String[]{"Analyst1"}, "SampleDbReport", columns, filters, groupFilters);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}
//	public void testHiveProd(){
	//	ReportConfigProvider.ReportDirectory = "*/examples/server/dbReport/report/hive/test/ReportConfig.properties";
	//	ODServiceReportReader.DataLocation="./src/examples/server/dbReport/report/hive/test/";
	//	yogi.base.io.resource.db.hive.HiveDbResource.JDBCUrl="jdbc:hive2://bdhmgp01.pdc.aa.com:10000/cuscore";

	/*	ODServiceReportReader.numOfThreads=3;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("age", "LessThan", "5", ""));
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("pe_ml_bal", "pe_ml_bal", "", false, 0, 0));
		columns.add(new Column("start_dt", "start_dt", "", false, 0, 0));
		columns.add(new Column("loyalty_score", "loyalty_score", "", false, 0, 0));
		columns.add(new Column("top1_accrue_cat", "top1_accrue_cat", "", false, 0, 0));
		columns.add(new Column("gender", "gender", "", false, 0, 0));
		columns.add(new Column("age", "age", "", false, 0, 0));
		
		Query query = new Query("DbReport", new String[]{"Analyst1"}, "SampleDbReport", columns, filters, groupFilters);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}
	
	public void testImpala(){
*/	//	ReportConfigProvider.ReportDirectory = "*/examples/server/dbReport/report/hive/test/ReportConfig.properties";
	//	ODServiceReportReader.DataLocation="./src/examples/server/dbReport/report/hive/test/";
	//	yogi.base.io.resource.db.hive.HiveDbResource.JDBCUrl="jdbc:impala://bdhcld01.hdq.aa.com:21050/cuscore";
	/*	yogi.base.io.resource.db.hive.HiveDbResource.JDBCDriver = "com.cloudera.impala.jdbc4.Driver";
		ODServiceReportReader.numOfThreads=3;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("age", "LessThan", "5", ""));
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("pe_ml_bal", "pe_ml_bal", "", false, 0, 0));		
		columns.add(new Column("start_dt", "start_dt", "", false, 0, 0));
		columns.add(new Column("loyalty_score", "loyalty_score", "", false, 0, 0));
		columns.add(new Column("top1_accrue_cat", "top1_accrue_cat", "", false, 0, 0));
		columns.add(new Column("gender", "gender", "", false, 0, 0));
		columns.add(new Column("age", "age", "", false, 0, 0));
		
		Query query = new Query("DbReport", new String[]{"Analyst1"}, "SampleDbReport", columns, filters, groupFilters);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}

	public void test(){
		JsonAssistant.get().fromJson("{\"domestic\":false,\"userId\":\"AA627674\"}", DummyDataSetsCommand.class);
	}*/
}
