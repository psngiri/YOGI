package yogi.remote.client.loadbalancer;

import java.util.List;

import yogi.base.util.Pair;
import yogi.remote.CommandException;
import yogi.remote.CommandServer;

public class FailOverLoadBalancer extends LoadBalancer {
	private Pair<String, CommandServer> commandServerPair = null;
	
	public Pair<String, CommandServer> getCommandServer() throws  CommandException{
		if(commandServerPair != null) return commandServerPair;
		List<String> commandServerAddressesColonPortNumbers = getCommandServerAddressesColonPortNumbers();
		for(String ipAddressCollonPortNumber: commandServerAddressesColonPortNumbers)
		{
			Pair<String, CommandServer> rtnValue = connect(ipAddressCollonPortNumber);
			if(rtnValue != null){
				commandServerPair = rtnValue;
				return rtnValue;
			}
		}
		throw new CommandException("Could not connect to any of the servers:" + commandServerAddressesColonPortNumbers);
	}

	
	@Override
	public void setServerNotLive(Pair<String, CommandServer> serverPair) {
		commandServerPair = null;
	}

}
