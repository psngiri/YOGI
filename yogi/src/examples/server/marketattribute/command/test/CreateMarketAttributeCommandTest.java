package examples.server.marketattribute.command.test;

import junit.framework.TestCase;

import yogi.base.app.Executor;
import yogi.base.app.testing.TestErrorReporter;
import yogi.remote.gson.GsonCommand;
import com.google.gson.Gson;

import examples.server.marketattribute.command.CreateMarketAttributeCommand;


public class CreateMarketAttributeCommandTest extends TestCase{

	public void test()
	{
		CreateMarketAttributeCommand createMarketAttributeCommand = new CreateMarketAttributeCommand("test", 0, "DFWCHI", "test Attribute");
		Gson gson = new Gson();
		String value = gson.toJson(createMarketAttributeCommand);
		System.out.println(value);		
		String name = CreateMarketAttributeCommand.class.getName();
		System.out.println(name);		
		GsonCommand gsonCommand = new GsonCommand(name, value, "test");
		String output = Executor.get().execute(gsonCommand);
		System.out.println(output);		
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
