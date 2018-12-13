package yogi.remote.client.app.test;

import java.util.logging.Level;

import yogi.base.app.testing.TestErrorReporter;
import yogi.remote.CommandException;
import yogi.remote.client.CommandClient;
import yogi.remote.client.app.CommandExecutor;

import junit.framework.TestCase;


public class CommandExecutorTestServer extends TestCase{

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

	public void test() throws CommandException
	{
		CommandExecutor.commandServerAddressesColonPortNumbers="localhost:6080";
		CommandClient.LoadBalancerClassName = "yogi.remote.client.loadbalancer.FailOverLoadBalancer";
		TestCommand testCommand = new TestCommand(10, Level.INFO);
		Integer rtnValue = CommandExecutor.get().execute(testCommand);
		assertEquals(10, rtnValue.intValue());
	}

	public void testFailOver()
	{
		CommandExecutor.commandServerAddressesColonPortNumbers="localhost:6081;localhost:6082;localhost:6080";
		CommandClient.LoadBalancerClassName = "yogi.remote.client.loadbalancer.FailOverLoadBalancer";
		TestCommand testCommand = new TestCommand(10, Level.INFO);
		while(true){
			try {
				Integer rtnValue = CommandExecutor.get().execute(testCommand);
				assertEquals(10, rtnValue.intValue());
				Thread.sleep(1000);
			} catch (CommandException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void testRoundRobin()
	{
		CommandExecutor.commandServerAddressesColonPortNumbers="localhost:6081;localhost:6082;localhost:6080";
		CommandClient.LoadBalancerClassName = "yogi.remote.client.loadbalancer.RoundRobinLoadBalancer";
		TestCommand testCommand = new TestCommand(10, Level.INFO);
		while(true){
			try {
				Integer rtnValue = CommandExecutor.get().execute(testCommand);
				assertEquals(10, rtnValue.intValue());
				Thread.sleep(1000);
			} catch (CommandException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
