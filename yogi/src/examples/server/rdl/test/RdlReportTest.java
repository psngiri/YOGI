package examples.server.rdl.test;

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

import examples.server.rdl.io.RdlReportReader;
import junit.framework.TestCase;

public class RdlReportTest extends TestCase {

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
		
		ReportConfigProvider.ReportDirectory = "*/examples/server/rdl/test/ReportConfig.properties";
		RdlReportReader.DataLocation="./src/examples/server/rdl/test/";
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("ID", "In", "1,2,3,4,5", ""));
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("ID", "ID", "", false, 0, 0));
		columns.add(new Column("Carrier", "Carrier", "", false, 0, 0));
		columns.add(new Column("FlightNumber", "FlightNumber", "", false, 0, 0));
		columns.add(new Column("DepartureAiport", "DepartureAiport", "", false, 0, 0));
		columns.add(new Column("ArrivalAirport", "ArrivalAirport", "", false, 0, 0));
		columns.add(new Column("Equipment", "Equipment", "", false, 0, 0));
		columns.add(new Column("ElapsedTime", "ElapsedTime", "", false, 0, 0));
		columns.add(new Column("GCDMiles", "GCDMiles", "", false, 0, 0));
		Query query = new Query("IFS", new String[]{"dataset1"}, "Legs", columns, filters, groupFilters);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}

}
