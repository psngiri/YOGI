package examples.server.oaAvailability.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.base.util.JsonAssistant;
import yogi.report.server.Column;
import yogi.report.server.Filter;
import yogi.report.server.Query;
import yogi.report.server.ReportData;
import yogi.report.server.ReportServerImpl;
import yogi.report.server.ReportTableData;
import yogi.report.server.config.ReportConfigProvider;

import examples.server.nonstopop.io.NonStopOpReportReader;
import examples.server.oaAvailability.io.OaAvailablityReportReader;

public class OaAvailabilityReportTest1 extends TestCase {

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


	public void test(){
		ReportConfigProvider.ReportDirectory = "*/examples/server/oaAvailability/test/ReportConfig.properties";
		OaAvailablityReportReader.DataLocation="./src/examples/server/oaAvailability/test/";
		NonStopOpReportReader.numOfThreads=1;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("Column1", "Column1", "", false, 0, 0));
		columns.add(new Column("Column2", "Column2", "", false, 0, 0));
		columns.add(new Column("Column3", "Column3", "", false, 0, 0));
		columns.add(new Column("Column4", "Column4", "", false, 0, 0));
		columns.add(new Column("Column5", "Column5", "", false, 0, 0));
		columns.add(new Column("Column6", "Column6", "", false, 0, 0));
		columns.add(new Column("Column7", "Column7", "", false, 0, 0));
		columns.add(new Column("Column8", "Column8", "", false, 0, 0));
		columns.add(new Column("Column9", "Column9", "", false, 0, 0));
		columns.add(new Column("Column10", "Column10", "", false, 0, 0));
		Query query = new Query("RM", new String[]{"OAAvailabilityTestFile.dat.gz"}, "OaAvailability", columns, filters, groupFilters);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}
}
