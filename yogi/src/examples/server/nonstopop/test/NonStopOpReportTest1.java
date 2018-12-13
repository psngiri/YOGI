package examples.server.nonstopop.test;

import java.util.ArrayList;
import java.util.List;

import yogi.base.app.testing.TestErrorReporter;
import yogi.base.util.JsonAssistant;
import yogi.report.server.Column;
import yogi.report.server.CompoundFilter;
import yogi.report.server.Filter;
import yogi.report.server.Query;
import yogi.report.server.ReportData;
import yogi.report.server.ReportServerImpl;
import yogi.report.server.ReportTableData;
import yogi.report.server.config.ReportConfigProvider;
import yogi.report.server.sql.SqlQueryUtil;

import examples.server.nonstopop.io.NonStopOpReportReader;
import junit.framework.TestCase;

public class NonStopOpReportTest1 extends TestCase {

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


	public void testCompoundFilter(){
		ReportConfigProvider.ReportDirectory = "*/examples/server/nonstopop/test/ReportConfig.properties";
		NonStopOpReportReader.DataLocation="./src/examples/server/nonstopop/test/";
		NonStopOpReportReader.numOfThreads=1;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("flightNumber", "In", "1979,7515,3100", ""));
		filters.add(new Filter("Segment", "In", "JFKLAX,DFWMIA,DFWTPA", ""));
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("legType", "legType", "Sample", false, 0, 0));
		columns.add(new Column("legKey", "legKey", "Sample", false, 0, 0));
		columns.add(new Column("airlineCode", "airlineCode", "Sample", false, 0, 0));
		columns.add(new Column("MktAirline", "MktAirline", "Sample", false, 0, 0));
		columns.add(new Column("flightNumber", "flightNumber", "Sample", false, 0, 0));
		columns.add(new Column("equipCode", "equipCode", "Sample", false, 0, 0));
		columns.add(new Column("onboardRevenue", "onboardRevenue", "Sum", false, 0, 0));
		columns.add(new Column("departureAirportCode", "departureAirportCode", "Sample", false, 0, 0));
		columns.add(new Column("arrivalAirportCode", "arrivalAirportCode", "Sample", false, 0, 0));
		columns.add(new Column("Segment", "Segment", "Sample", false, 0, 0));
		columns.add(new Column("SegmentND", "SegmentND", "Sample", false, 0, 0));
		CompoundFilter compoundfilter = new CompoundFilter(false, new CompoundFilter(true, new CompoundFilter(new Filter("legKey", "In", "3", "")), new CompoundFilter(new Filter("airlineCode", "In", "QF", ""))),
				new CompoundFilter(true, new CompoundFilter(new Filter("legKey", "In", "13", "")), new CompoundFilter(new Filter("airlineCode", "In", "JL", ""))));
		Query query = new Query("IFS", new String[]{"Forecast1"}, "NonStopOp", columns, filters, groupFilters, compoundfilter);
		String userId = "AA549915";
		
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
		System.out.println(SqlQueryUtil.buildSqlQuery(query));;
		System.out.println(JsonAssistant.get().toJson(compoundfilter));
	}
	
	public void test(){
		ReportConfigProvider.ReportDirectory = "*/examples/server/nonstopop/test/ReportConfig.properties";
		NonStopOpReportReader.DataLocation="./src/examples/server/nonstopop/test/";
		NonStopOpReportReader.numOfThreads=1;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("flightNumber", "In", "1979,7515,3100", ""));
		filters.add(new Filter("Segment", "In", "JFKLAX,DFWMIA,DFWTPA", ""));
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("legType", "legType", "Sample", false, 0, 0));
		columns.add(new Column("legKey", "legKey", "Sample", false, 0, 0));
		columns.add(new Column("airlineCode", "airlineCode", "Sample", false, 0, 0));
		columns.add(new Column("MktAirline", "MktAirline", "Sample", false, 0, 0));
		columns.add(new Column("flightNumber", "flightNumber", "Sample", false, 0, 0));
		columns.add(new Column("equipCode", "equipCode", "Sample", false, 0, 0));
		columns.add(new Column("onboardRevenue", "onboardRevenue", "Sum", false, 0, 0));
		columns.add(new Column("departureAirportCode", "departureAirportCode", "Sample", false, 0, 0));
		columns.add(new Column("arrivalAirportCode", "arrivalAirportCode", "Sample", false, 0, 0));
		columns.add(new Column("Segment", "Segment", "Sample", false, 0, 0));
		columns.add(new Column("SegmentND", "SegmentND", "Sample", false, 0, 0));
		Query query = new Query("IFS", new String[]{"Forecast1"}, "NonStopOp", columns, filters, groupFilters);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
		System.out.println(SqlQueryUtil.buildSqlQuery(query));;
	}
	
	public void test1(){
		ReportConfigProvider.ReportDirectory = "*/examples/server/nonstopop/test/ReportConfig.properties";
		NonStopOpReportReader.DataLocation="./src/examples/server/nonstopop/test/";
		NonStopOpReportReader.numOfThreads=1;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("airlineCode", "airlineCode", "Sample", true, 0, 0));
		columns.add(new Column("Hub", "Hub", "", true, 0, 0));
		columns.add(new Column("Segment", "Segment", "Sample", true, 0, 0));
		columns.add(new Column("onboardRevenue", "onboardRevenue", "Sum", false, 0, 0));
		Query query = new Query("IFS", new String[]{"Forecast1"}, "NonStopOp", columns, filters, groupFilters, true, false);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}
	
}
