package examples.server.marketattribute.command.test;

import junit.framework.TestCase;

import yogi.base.app.Executor;
import yogi.base.app.testing.TestErrorReporter;
import yogi.remote.gson.GsonCommand;
import com.google.gson.Gson;

import examples.server.marketattribute.MarketAttributeRecord;
import examples.server.marketattribute.command.CreateMarketAttributeCommand;
import examples.server.marketattribute.command.GetMarketAttributesCommand;


public class GetMarketAttributesCommandTest extends TestCase{

	public void test()
	{
		createMarektAttribute(0, "DFWCHI", "test attribute");
		createMarektAttribute(0, "DFWJFK", "test attribute1");
		createMarektAttribute(0, "DFWSFO", "test attribute2");
		createMarektAttribute(1, "DFWSFO", "test attribute2");
		GetMarketAttributesCommand getMarketAttributesCommand = new GetMarketAttributesCommand("test", "DFWCHI", "DFWJFK", "DFWSFO");
		Gson gson = new Gson();
		String value = gson.toJson(getMarketAttributesCommand);
		System.out.println(value);		
		String name = GetMarketAttributesCommand.class.getName();
		System.out.println(name);		
		GsonCommand gsonCommand = new GsonCommand(name, value, null);
		String output = Executor.get().execute(gsonCommand);
		System.out.println(output);		
	}

	public MarketAttributeRecord createMarektAttribute(int action, String market, String attribute){
		CreateMarketAttributeCommand createMarketAttributeCommand = new CreateMarketAttributeCommand("test", action, market, attribute);
		return Executor.get().execute(createMarketAttributeCommand);

	}
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		TestErrorReporter.end();
		super.tearDown();
	}
}
