package examples.server.marketattribute.command.test;

import java.util.List;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.Executor;
import yogi.base.app.testing.TestErrorReporter;
import yogi.remote.gson.GsonCommand;
import com.google.gson.Gson;

import examples.server.marketattribute.MarketAttributeRecord;
import examples.server.marketattribute.command.CreateMarketAttributeCommand;
import examples.server.marketattribute.command.GetMarketAttributesAtTimestampCommand;
import examples.server.marketattribute.command.GetMarketAttributesHistoryCommand;


public class GetMarketAttributesAtTimestampCommandTest extends TestCase{

	public void test() throws InterruptedException
	{
		createMarektAttribute(0, "DFWCHI", "test attribute");
		Thread.sleep(5);
		createMarektAttribute(0, "DFWCHI", "test attribute1");
		Thread.sleep(5);
		createMarektAttribute(0, "DFWCHI", "test attribute2");
		Thread.sleep(5);
		createMarektAttribute(1, "DFWCHI", "test attribute3");
		long timeStamp = System.currentTimeMillis();
		boolean includeCancel = true;
		GetMarketAttributesAtTimestampCommand getMarketAttributesAtTimestampCommand = new GetMarketAttributesAtTimestampCommand("test", timeStamp, includeCancel, "DFWCHI");
		Gson gson = new Gson();
		String value = gson.toJson(getMarketAttributesAtTimestampCommand);
		System.out.println(value);		
		String name = GetMarketAttributesAtTimestampCommand.class.getName();
		System.out.println(name);		
		GsonCommand gsonCommand = new GsonCommand(name, value, null);
		String output = Executor.get().execute(gsonCommand);
		System.out.println(output);		
		GetMarketAttributesHistoryCommand getMarketAttributesHistoryCommand = new GetMarketAttributesHistoryCommand("test", "DFWCHI");
		List<MarketAttributeRecord> history = Executor.get().execute(getMarketAttributesHistoryCommand);
		assertEquals(4, history.size());
		MarketAttributeRecord marketAttributeRecord = history.get(0);
		getMarketAttributesAtTimestampCommand = new GetMarketAttributesAtTimestampCommand("test", marketAttributeRecord.getTimeStamp(), includeCancel, "DFWCHI");
		assertEquals(marketAttributeRecord, Executor.get().execute(getMarketAttributesAtTimestampCommand).get(0));
		marketAttributeRecord = history.get(2);
		getMarketAttributesAtTimestampCommand = new GetMarketAttributesAtTimestampCommand("test", marketAttributeRecord.getTimeStamp()+2, includeCancel, "DFWCHI");
		assertEquals(marketAttributeRecord, Executor.get().execute(getMarketAttributesAtTimestampCommand).get(0));
		getMarketAttributesAtTimestampCommand = new GetMarketAttributesAtTimestampCommand("test", timeStamp, false, "DFWCHI");
		assertEquals(true, Executor.get().execute(getMarketAttributesAtTimestampCommand).isEmpty());
	}

	public MarketAttributeRecord createMarektAttribute(int action, String market, String attribute){
		CreateMarketAttributeCommand createMarketAttributeCommand = new CreateMarketAttributeCommand("test", action, market, attribute);
		return Executor.get().execute(createMarketAttributeCommand);

	}
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Factory.clearAllFactories();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		TestErrorReporter.end();
		Factory.clearAllFactories();
		super.tearDown();
	}
}
