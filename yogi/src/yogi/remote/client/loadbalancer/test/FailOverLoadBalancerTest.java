package yogi.remote.client.loadbalancer.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.util.Pair;
import yogi.remote.CommandException;
import yogi.remote.CommandServer;
import yogi.remote.client.loadbalancer.FailOverLoadBalancer;

public class FailOverLoadBalancerTest extends TestCase {

	public void test() throws InterruptedException, CommandException
	{
		MyFailOverLoadBalancer failOverLoadBalancer = new MyFailOverLoadBalancer();
		Pair<String, CommandServer> serverPair = failOverLoadBalancer.getCommandServer();
		assertEquals("Server1", serverPair.getFirst());
		failOverLoadBalancer.setServerNotLive(serverPair);
		serverPair = failOverLoadBalancer.getCommandServer();
		assertEquals("Server1", serverPair.getFirst());
		failOverLoadBalancer.setFlag(true);
		failOverLoadBalancer.setServerNotLive(serverPair);
		serverPair = failOverLoadBalancer.getCommandServer();
		assertEquals("Server2", serverPair.getFirst());
		
	}
	
	class MyFailOverLoadBalancer extends FailOverLoadBalancer{
		boolean flag = false;
		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		@Override
		protected List<String> getCommandServerAddressesColonPortNumbers() {
			List<String> rtnValue = new ArrayList<String>(2);
			rtnValue.add("Server1");
			rtnValue.add("Server2");
			return rtnValue;
		}

		@Override
		protected Pair<String, CommandServer> connect(String ipAddressCollonPortNumber) {
			if(flag && ipAddressCollonPortNumber.equals("Server1")) return null;
			System.out.println("Connecting to " + ipAddressCollonPortNumber);
			return new Pair<String, CommandServer>(ipAddressCollonPortNumber, null);
		}
		
	}
}
