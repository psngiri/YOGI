package yogi.remote.client.loadbalancer;

import java.rmi.RemoteException;

import yogi.base.util.Pair;
import yogi.cluster.SwitchPrimaryCommand;
import yogi.remote.CommandServer;

public class PrimaryFailOverLoadBalancer extends FailOverLoadBalancer {
	public PrimaryFailOverLoadBalancer() {
		super();
	}

	@Override
	protected Pair<String, CommandServer> connect(String ipAddressCollonPortNumber) {
		Pair<String, CommandServer> serverPair = super.connect(ipAddressCollonPortNumber);
		if(serverPair != null){
			try {
				
				serverPair.getSecond().execute(new SwitchPrimaryCommand(true));
			} catch (RemoteException e) {
				throw new RuntimeException("Error in sending SwitchClusterCommand", e);
			}
		}
		return serverPair;
	}

}
