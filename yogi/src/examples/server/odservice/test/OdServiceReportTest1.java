package examples.server.odservice.test;

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

public class OdServiceReportTest1 extends TestCase {

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
	public void testPOOProrate(){
		ReportConfigProvider.ReportDirectory = "*/examples/server/odservice/test/ReportConfig.properties";
		ODServiceReportReader.DataLocation="./src/examples/server/odservice/test/";
		ODServiceReportReader.numOfThreads=3;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("OD", "In", "AAECDG", ""));
		List<Filter> groupFilters = new ArrayList<Filter>();
//		groupFilters.add(new Filter("ProrateCarrier", "In", "AF", ""));
//		groupFilters.add(new Filter("ProrateSegment", "In", "ALGCDG", ""));
//		groupFilters.add(new Filter("ProrateRevenue", "GreaterThan", "0", ""));
		columns.add(new Column("OD", "OD", "", true, 0, 0));
		columns.add(new Column("POO", "POO", "", true, 0, 0));
		columns.add(new Column("ProrateCarrier", "ProrateCarrier", "", true, 0, 0));
//		columns.add(new Column("ProrateSegment", "ProrateSegment", "", true, 0, 0));
//		columns.add(new Column("LegSegment", "LegSegment", "", false, 0, 0));
//		columns.add(new Column("legAirline", "legAirline", "", false, 0, 0));
//		columns.add(new Column("legFareRatio", "legFareRatio", "", false, 0, 0));
//		columns.add(new Column("PooRevenue", "PooRevenue", "Sum", false, 0, 0));
		columns.add(new Column("ProrateFactor", "ProrateFactor", "Sum", false, 0, 0));
		columns.add(new Column("ProrateRevenueUpDown", "ProrateRevenueUpDown", "Sum", false, 0, 0));
//		columns.add(new Column("PooProrateRevenue", "PooProrateRevenue", "Sum", false, 0, 0));
		columns.add(new Column("ProrateRevenue", "ProrateRevenue", "Sum", false, 0, 0));
		columns.add(new Column("revenue", "revenue", "Sum", false, 0, 0));
		Query query = new Query("IFS", new String[]{"Forecast1"}, "ODService", columns, filters, groupFilters,true,false);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}
	
	public void testPOO(){
		ReportConfigProvider.ReportDirectory = "*/examples/server/odservice/test/ReportConfig.properties";
		ODServiceReportReader.DataLocation="./src/examples/server/odservice/test/";
		ODServiceReportReader.numOfThreads=3;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("POO", "POO", "", true, 0, 0));
		columns.add(new Column("PooObservedPassengers", "PooObservedPassengers", "Sum", false, 0, 0));
		Query query = new Query("IFS", new String[]{"Forecast1"}, "ODService", columns, filters, groupFilters,true,false);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}

	public void testOpLegsAAProrateRevenue(){
		ReportConfigProvider.ReportDirectory = "*/examples/server/odservice/test/ReportConfig.properties";
		ODServiceReportReader.DataLocation="./src/examples/server/odservice/test/";
		ODServiceReportReader.numOfThreads=3;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("ODkey", "In", "100", ""));
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("serviceType", "serviceType", "", false, 0, 0));
		columns.add(new Column("ODkey", "ODkey", "", false, 0, 0));
		columns.add(new Column("revenue", "revenue", "", false, 0, 0));
		
		columns.add(new Column("OD", "OD", "", false, 0, 0));
		columns.add(new Column("ODND", "ODND", "", false, 0, 0));
		columns.add(new Column("LegSegmentNew", "LegSegmentNew", "", false, 0, 0));
		columns.add(new Column("LegSegmentNDNew", "LegSegmentNDNew", "", false, 0, 0));
		columns.add(new Column("legAirline", "LegAirline", "", false, 0, 0));
		columns.add(new Column("LegAirlineSegmentNew", "LegAirlineSegmentNew", "", false, 0, 0));
		columns.add(new Column("LegAirlineSegmentNDNew", "LegAirlineSegmentNDNew", "", false, 0, 0));
		columns.add(new Column("OpLegAirlineSegment", "OpLegAirlineSegment", "", false, 0, 0));
		columns.add(new Column("operationalSchedSvcKey", "operationalSchedSvcKey", "", false, 0, 0));
		columns.add(new Column("opLegAirline1", "opLegAirline1", "", false, 0, 0));
		columns.add(new Column("opLegAirline2", "opLegAirline2", "", false, 0, 0));
		columns.add(new Column("opLegAirline3", "opLegAirline3", "", false, 0, 0));
		columns.add(new Column("opLegAirline4", "opLegAirline4", "", false, 0, 0));
		columns.add(new Column("opLegAirline5", "opLegAirline5", "", false, 0, 0));
		columns.add(new Column("opLegAirline6", "opLegAirline6", "", false, 0, 0));
		columns.add(new Column("fareRatio1", "fareRatio1", "", false, 0, 0));
		columns.add(new Column("fareRatio2", "fareRatio2", "", false, 0, 0));
		columns.add(new Column("fareRatio3", "fareRatio3", "", false, 0, 0));
		columns.add(new Column("fareRatio4", "fareRatio4", "", false, 0, 0));
		columns.add(new Column("fareRatio5", "fareRatio5", "", false, 0, 0));
		columns.add(new Column("fareRatio6", "fareRatio6", "", false, 0, 0));
		columns.add(new Column("revenue", "revenue", "", false, 0, 0));
		columns.add(new Column("AAProrateRevenue", "AAProrateRevenue", "", false, 0, 0));
		Query query = new Query("IFS", new String[]{"Forecast1"}, "ODService", columns, filters, groupFilters);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}
	
	public void testOpLegsNS(){
		ReportConfigProvider.ReportDirectory = "*/examples/server/odservice/test/ReportConfig.properties";
		ODServiceReportReader.DataLocation="./src/examples/server/odservice/test/";
		ODServiceReportReader.numOfThreads=3;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("ODkey", "In", "100", ""));
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("serviceType", "serviceType", "", false, 0, 0));
		columns.add(new Column("ODkey", "ODkey", "", false, 0, 0));
		columns.add(new Column("revenue", "revenue", "", false, 0, 0));
		
		columns.add(new Column("OD", "OD", "", false, 0, 0));
		columns.add(new Column("ODND", "ODND", "", false, 0, 0));
		columns.add(new Column("LegSegmentNew", "LegSegmentNew", "", false, 0, 0));
		columns.add(new Column("LegSegmentNDNew", "LegSegmentNDNew", "", false, 0, 0));
		columns.add(new Column("legAirline", "LegAirline", "", false, 0, 0));
		columns.add(new Column("LegAirlineSegmentNew", "LegAirlineSegmentNew", "", false, 0, 0));
		columns.add(new Column("LegAirlineSegmentNDNew", "LegAirlineSegmentNDNew", "", false, 0, 0));
		columns.add(new Column("OpLegAirlineSegment", "OpLegAirlineSegment", "", false, 0, 0));
		columns.add(new Column("operationalSchedSvcKey", "operationalSchedSvcKey", "", false, 0, 0));
		columns.add(new Column("opLegAirline1", "opLegAirline1", "", false, 0, 0));
		columns.add(new Column("opLegAirline2", "opLegAirline2", "", false, 0, 0));
		columns.add(new Column("opLegAirline3", "opLegAirline3", "", false, 0, 0));
		columns.add(new Column("opLegAirline4", "opLegAirline4", "", false, 0, 0));
		columns.add(new Column("opLegAirline5", "opLegAirline5", "", false, 0, 0));
		columns.add(new Column("opLegAirline6", "opLegAirline6", "", false, 0, 0));
		columns.add(new Column("legAirline1NS", "legAirline1NS", "", false, 0, 0));
		columns.add(new Column("legAirline2NS", "legAirline2NS", "", false, 0, 0));
		columns.add(new Column("legAirline3NS", "legAirline3NS", "", false, 0, 0));
		columns.add(new Column("legAirline4NS", "legAirline4NS", "", false, 0, 0));
		columns.add(new Column("legAirline5NS", "legAirline5NS", "", false, 0, 0));
		columns.add(new Column("legAirline6NS", "legAirline6NS", "", false, 0, 0));
		columns.add(new Column("OpLegAirlineSegment", "OpLegAirlineSegment", "", false, 0, 0));
		columns.add(new Column("legAirlineSegmentNS", "legAirlineSegmentNS", "", false, 0, 0));
		Query query = new Query("IFS", new String[]{"Forecast1"}, "ODService", columns, filters, groupFilters);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}
	
	public void testOpLegs(){
		ReportConfigProvider.ReportDirectory = "*/examples/server/odservice/test/ReportConfig.properties";
		ODServiceReportReader.DataLocation="./src/examples/server/odservice/test/";
		ODServiceReportReader.numOfThreads=3;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("OpLegAirlineSegment", "Like", "%AHALGCDG%", ""));
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("serviceType", "serviceType", "", false, 0, 0));
		columns.add(new Column("ODkey", "ODkey", "", false, 0, 0));
		columns.add(new Column("revenue", "revenue", "", false, 0, 0));
		
		columns.add(new Column("OD", "OD", "", false, 0, 0));
		columns.add(new Column("ODND", "ODND", "", false, 0, 0));
		columns.add(new Column("LegSegment", "LegSegment", "", false, 0, 0));
		columns.add(new Column("legAirline", "LegAirline", "", false, 0, 0));
		columns.add(new Column("LegAirlineSegment", "LegAirlineSegment", "", false, 0, 0));
		columns.add(new Column("OpLegAirlineSegment", "OpLegAirlineSegment", "", false, 0, 0));
		columns.add(new Column("operationalSchedSvcKey", "operationalSchedSvcKey", "", false, 0, 0));
		columns.add(new Column("opLegAirline1", "opLegAirline1", "", false, 0, 0));
		columns.add(new Column("opLegAirline2", "opLegAirline2", "", false, 0, 0));
		columns.add(new Column("opLegAirline3", "opLegAirline3", "", false, 0, 0));
		columns.add(new Column("opLegAirline4", "opLegAirline4", "", false, 0, 0));
		columns.add(new Column("opLegAirline5", "opLegAirline5", "", false, 0, 0));
		columns.add(new Column("opLegAirline6", "opLegAirline6", "", false, 0, 0));
		Query query = new Query("IFS", new String[]{"Forecast1"}, "ODService", columns, filters, groupFilters);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}

	public void testFile(){
		
		ReportConfigProvider.ReportDirectory = "*/examples/server/odservice/test/ReportConfig.properties";
		ODServiceReportReader.DataLocation="./src/examples/server/odservice/test/";
		yogi.report.condition.BaseInCondition.UploadedDataFileLocation = "C:/temp/";
		ODServiceReportReader.numOfThreads=3;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("OD", "In", "File:testFile.txt:0", "aaa"));
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("serviceType", "serviceType", "", false, 0, 0));
		columns.add(new Column("ODkey", "ODkey", "", false, 0, 0));
		columns.add(new Column("numberOfConnections", "numberOfConnections", "", false, 0, 0));
		columns.add(new Column("revenue", "revenue", "", false, 0, 0));
		columns.add(new Column("RevenuePerObservedPax", "RevenuePerObservedPax", "", false, 0, 0));
		columns.add(new Column("observedPassengers", "observedPassengers", "", false, 0, 0));
		columns.add(new Column("departureAirportCode", "departureAirportCode", "", false, 0, 0));
		columns.add(new Column("arrivalAirportCode", "arrivalAirportCode", "", false, 0, 0));
		columns.add(new Column("OD", "OD", "", false, 0, 0));
		columns.add(new Column("ODND", "ODND", "", false, 0, 0));
		Query query = new Query("IFS", new String[]{"Forecast1"}, "ODService", columns, filters, groupFilters);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}
	
	public void test(){
		
		ReportConfigProvider.ReportDirectory = "*/examples/server/odservice/test/ReportConfig.properties";
		ODServiceReportReader.DataLocation="./src/examples/server/odservice/test/";
		ODServiceReportReader.numOfThreads=3;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("OD", "In", "AAEALC", "aaa"));
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("serviceType", "serviceType", "", false, 0, 0));
		columns.add(new Column("ODkey", "ODkey", "", false, 0, 0));
		columns.add(new Column("numberOfConnections", "numberOfConnections", "", false, 0, 0));
		columns.add(new Column("revenue", "revenue", "", false, 0, 0));
		columns.add(new Column("RevenuePerObservedPax", "RevenuePerObservedPax", "", false, 0, 0));
		columns.add(new Column("observedPassengers", "observedPassengers", "", false, 0, 0));
		columns.add(new Column("departureAirportCode", "departureAirportCode", "", false, 0, 0));
		columns.add(new Column("arrivalAirportCode", "arrivalAirportCode", "", false, 0, 0));
		columns.add(new Column("OD", "OD", "", false, 0, 0));
		columns.add(new Column("ODND", "ODND", "", false, 0, 0));
		Query query = new Query("IFS", new String[]{"Forecast1"}, "ODService", columns, filters, groupFilters);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}
	public void testLegSegment(){
		
		ReportConfigProvider.ReportDirectory = "*/examples/server/odservice/test/ReportConfig.properties";
		ODServiceReportReader.DataLocation="./src/examples/server/odservice/test/";
		ODServiceReportReader.numOfThreads=1;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("OD", "In", "AAEALC", "aaa"));
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("serviceType", "serviceType", "", false, 0, 0));
		columns.add(new Column("ODkey", "ODkey", "", false, 0, 0));
		columns.add(new Column("numberOfConnections", "numberOfConnections", "", false, 0, 0));
		columns.add(new Column("revenue", "revenue", "", false, 0, 0));
		columns.add(new Column("RevenuePerObservedPax", "RevenuePerObservedPax", "", false, 0, 0));
		columns.add(new Column("observedPassengers", "observedPassengers", "", false, 0, 0));
		columns.add(new Column("departureAirportCode", "departureAirportCode", "", false, 0, 0));
		columns.add(new Column("arrivalAirportCode", "arrivalAirportCode", "", false, 0, 0));
		columns.add(new Column("OD", "OD", "", false, 0, 0));
		columns.add(new Column("ODND", "ODND", "", false, 0, 0));
		columns.add(new Column("departureAirportCode1", "departureAirportCode1", "", false, 0, 0));
		columns.add(new Column("arrivalAirportCode1", "arrivalAirportCode1", "", false, 0, 0));
		columns.add(new Column("departureAirportCode2", "departureAirportCode2", "", false, 0, 0));
		columns.add(new Column("arrivalAirportCode2", "arrivalAirportCode2", "", false, 0, 0));
		columns.add(new Column("LegSegment", "LegSegment", "", false, 0, 0));
		columns.add(new Column("LegSegmentND", "LegSegmentND", "", false, 0, 0));
		columns.add(new Column("legKey", "legKey", "", false, 0, 0));
		columns.add(new Column("legAirline", "legAirline", "", false, 0, 0));
		Query query = new Query("IFS", new String[]{"Forecast1"}, "ODService", columns, filters, groupFilters);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}
	public void testGroup(){
		
		ReportConfigProvider.ReportDirectory = "*/examples/server/odservice/test/ReportConfig.properties";
		ODServiceReportReader.DataLocation="./src/examples/server/odservice/test/";
		ODServiceReportReader.numOfThreads=1;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("OD", "In", "AAEALC", "aaa"));
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("serviceType", "serviceType", "", true, 0, 0));
		columns.add(new Column("numberOfConnections", "numberOfConnections", "Sum", false, 0, 0));
		columns.add(new Column("revenue", "revenue", "Sum", false, 0, 0));
		columns.add(new Column("RevenuePerObservedPax", "RevenuePerObservedPax", "Sum", false, 0, 0));
		columns.add(new Column("observedPassengers", "observedPassengers", "Sum", false, 0, 0));
		columns.add(new Column("OD", "OD", "", true, 0, 0));
		Query query = new Query("IFS", new String[]{"Forecast1"}, "ODService", columns, filters, groupFilters,true,false);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}
	
	public void testCompare(){
		
		ReportConfigProvider.ReportDirectory = "*/examples/server/odservice/test/ReportConfig.properties";
		ODServiceReportReader.DataLocation="./src/examples/server/odservice/test/";
		ODServiceReportReader.numOfThreads=1;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("OD", "In", "AAEALC", "aaa"));
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("serviceType", "serviceType", "", false, 0, 0));
		columns.add(new Column("ODkey", "ODkey", "", false, 0, 0));
		columns.add(new Column("numberOfConnections", "numberOfConnections", "", false, 0, 0));
		columns.add(new Column("revenue", "revenue", "", new String[]{"Diff","Average"}, false, 0, 0));
		columns.add(new Column("RevenuePerObservedPax", "RevenuePerObservedPax", "", new String[]{"Diff","Average"}, false, 0, 0));
		columns.add(new Column("observedPassengers", "observedPassengers", "", false, 0, 0));
		columns.add(new Column("departureAirportCode", "departureAirportCode", "", false, 0, 0));
		columns.add(new Column("arrivalAirportCode", "arrivalAirportCode", "", false, 0, 0));
		columns.add(new Column("OD", "OD", "", false, 0, 0));
		columns.add(new Column("ODND", "ODND", "", false, 0, 0));
		Query query = new Query("IFS", new String[]{"Forecast1","Forecast1"}, "ODService", columns, filters, groupFilters);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}
	public void testMultipleForecasts(){
		
		ReportConfigProvider.ReportDirectory = "*/examples/server/odservice/test/ReportConfig.properties";
		ODServiceReportReader.DataLocation="./src/examples/server/odservice/test/";
		ODServiceReportReader.numOfThreads=1;
		ReportServerImpl rs = new ReportServerImpl();
		List<Column> columns = new ArrayList<Column>();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("OD", "In", "AAEALC", "aaa"));
		List<Filter> groupFilters = new ArrayList<Filter>();
		columns.add(new Column("serviceType", "serviceType", "", false, 0, 0));
		columns.add(new Column("ODkey", "ODkey", "", false, 0, 0));
		columns.add(new Column("numberOfConnections", "numberOfConnections", "", false, 0, 0));
		columns.add(new Column("revenue", "revenue", "", false, 0, 0));
		columns.add(new Column("RevenuePerObservedPax", "RevenuePerObservedPax", "", false, 0, 0));
		columns.add(new Column("observedPassengers", "observedPassengers", "", false, 0, 0));
		columns.add(new Column("departureAirportCode", "departureAirportCode", "", false, 0, 0));
		columns.add(new Column("arrivalAirportCode", "arrivalAirportCode", "", false, 0, 0));
		columns.add(new Column("OD", "OD", "", false, 0, 0));
		columns.add(new Column("ODND", "ODND", "", false, 0, 0));
		Query query = new Query("IFS", new String[]{"Forecast1","Forecast1"}, "ODService", columns, filters, groupFilters);
		String userId = "AA549915";
		ReportTableData data = rs.generateReport(query, userId);
		System.out.println(data.getHeader());
		List<ReportData> rows = data.getRows();
		for(ReportData rd: rows){
			System.out.println(JsonAssistant.get().toJson(rd.getValues()));
		}
	}
}
