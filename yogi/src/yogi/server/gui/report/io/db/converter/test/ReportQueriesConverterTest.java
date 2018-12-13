package yogi.server.gui.report.io.db.converter.test;


import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.base.app.testing.TestModule;
import yogi.remote.client.app.BaseCommandExecutor;
import yogi.remote.client.app.CommandExecutor;
import yogi.remote.client.app.MultiServerCommandExecutor;
import yogi.server.gui.report.io.db.converter.ReportQueriesConverter;

public class ReportQueriesConverterTest extends TestCase{

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		TestErrorReporter.start();
		setUpData();
	}

	@Override
	protected void tearDown() throws Exception
	{
		TestErrorReporter.end();
		Factory.clearAllFactories();
		super.tearDown();
	}

	private void setUpData()
	{	
		MultiServerCommandExecutor.get().execute(new TestModule());
		
		//DumpProperties.ColumnSeparator="\\|";
		BaseCommandExecutor.Initialized = true;
		CommandExecutor.UsePull = true;
				
		TestModule testModule = new TestModule();
		testModule.run();
	}

	private void convert(String str) {
		ReportQueriesConverter converter = new ReportQueriesConverter();
		System.out.println("Initial String : " + str);
		String convertedStr = converter.convert(str);
		System.out.println("String converted : " + !str.equalsIgnoreCase(convertedStr));
		System.out.println("Converterd String : " + convertedStr);
		System.out.println();
	}
	
	public void testEmpty() {
		convert("");
		convert("{}");
		convert("{\"query\":{}}");
		convert("{\"query\":{\"serverType\":\"DomesticPRE\",\"object\":[],\"reportName\":\"DomesticFareCumulative\",\"columns\":[],\"groupFilters\":[],\"singleGrouping\":false,\"groupReport\":false}}");
		convert("{\"query\":{\"serverType\":\"DomesticPRE\",\"object\":[],\"reportName\":\"DomesticFareCumulative\",\"columns\":[],\"filters\":[],\"groupFilters\":[],\"singleGrouping\":false,\"groupReport\":false}}");
			
	}
	
	public void testConversionNotNeeded() {
		convert("{\"query\":{\"serverType\":\"DomesticPRE\",\"object\":[\"-1\"],\"reportName\":\"DomesticFareCumulative\",\"columns\":[{\"name\":\"Market\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"Carrier\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"FareBasis\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"FareAmountWithTax\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"Sub\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0}],\"filters\":[{\"columnName\":\"Market\",\"condition\":\"In\",\"value\":\"atldfw\",\"alias\":\"\"},{\"columnName\":\"Carrier\",\"condition\":\"In\",\"value\":\"aa\",\"alias\":\"\"}],\"groupFilters\":[],\"singleGrouping\":false,\"groupReport\":false}}");
		convert("{\"query\":{\"serverType\":\"InternationalPRE\",\"object\":[],\"reportName\":\"InternationalFareCumulative\",\"columns\":[{\"name\":\"Market\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":1,\"groupSortOrder\":0},{\"name\":\"Origin\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"Destination\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"FareBasis\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"OWRT\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"RoutingNumber\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"FootNote\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"Currency\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"FareAmount\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"TariffName\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"ODMiles\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"Carrier\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"MarketReal\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"RuleNumber\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"Cat3-Seasonality/DateRange\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0}],\"filters\":[{\"columnName\":\"TariffName\",\"condition\":\"In\",\"value\":\"ATAA,aatcp\",\"alias\":\"\"},{\"columnName\":\"Carrier\",\"condition\":\"In\",\"value\":\"AA\",\"alias\":\"\"},{\"columnName\":\"Origin\",\"condition\":\"Location In\",\"value\":\"bdl,smf,yto,saf,cwa,par,lon,rom,dub\",\"alias\":\"\"},{\"columnName\":\"Destination\",\"condition\":\"Location In\",\"value\":\"bdl,smf,yto,saf,cwa,par,lon,rom,dub\",\"alias\":\"\"},{\"columnName\":\"FareBasis\",\"condition\":\"Like\",\"value\":\"?k%\",\"alias\":\"\"}],\"groupFilters\":[],\"singleGrouping\":false,\"groupReport\":false}}");
	}
	
	public void testValid() {	
		convert("{\"query\":{\"serverType\":\"DomesticPRE\",\"object\":[],\"reportName\":\"DomesticFareCumulative\",\"columns\":[{\"name\":\"Market\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"OriginReal\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"DestinationCityReal\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"Carrier\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"FareBasis\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"Currency\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"FareAmountWithTax\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"OWRT\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"FootNote\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"TariffNumber\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"RuleNumber\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"RoutingNumber\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0},{\"name\":\"Origin\",\"functionName\":\"\",\"compareFunctionNames\":[],\"groupBy\":false,\"sortOrder\":0,\"groupSortOrder\":0}],\"filters\":[{\"columnName\":\"Carrier\",\"condition\":\"In\",\"value\":\"WN\",\"alias\":\"\"},{\"columnName\":\"FareBasis\",\"condition\":\"In\",\"value\":\"X\",\"alias\":\"\"},{\"columnName\":\"TariffNumber\",\"condition\":\"In\",\"value\":\"815\",\"alias\":\"\"},{\"columnName\":\"Market\",\"condition\":\"In\",\"value\":\"DALSJU\",\"alias\":\"\"},{\"columnName\":\"OriginReal\",\"condition\":\"In\",\"value\":\"DFW\",\"alias\":\"\"}],\"groupFilters\":[],\"singleGrouping\":false,\"groupReport\":false}}");
	}	
}
