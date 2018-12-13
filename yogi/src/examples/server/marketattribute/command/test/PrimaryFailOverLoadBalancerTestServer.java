package examples.server.marketattribute.command.test;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.remote.CommandException;
import yogi.remote.client.CommandClient;
import yogi.remote.client.app.CommandExecutor;

import examples.server.marketattribute.MarketAttributeRecord;
import examples.server.marketattribute.command.CreateMarketAttributeCommand;


public class PrimaryFailOverLoadBalancerTestServer extends TestCase{

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

	public void testPrimaryFailOver() throws InterruptedException
	{
		CommandExecutor.commandServerAddressesColonPortNumbers="localhost:6081;localhost:6082;localhost:6080";
		CommandClient.LoadBalancerClassName = "yogi.remote.client.loadbalancer.PrimaryFailOverLoadBalancer";
		yogi.remote.client.CommandClient.NumberOfRetryAttempts=1;
		yogi.remote.client.CommandClient.SleepTimeBetweenRetries=1000;
		int i = 0;
		while(true){
			try {
				CreateMarketAttributeCommand createMarketAttributeCommand = new CreateMarketAttributeCommand("test", 0, "DFWCHI", "test Attribute" + i++);
				MarketAttributeRecord rtnValue = CommandExecutor.get().execute(createMarketAttributeCommand);
				System.out.println(rtnValue);
				Thread.sleep(10000);
			} catch (CommandException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
