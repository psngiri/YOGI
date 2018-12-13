package yogi.remote.client.loadbalancer;

import java.util.ArrayList;
import java.util.List;

import yogi.base.util.Pair;
import yogi.base.util.Timer;
import yogi.remote.CommandException;
import yogi.remote.CommandServer;

public class RoundRobinLoadBalancer extends LoadBalancer {
	private List<Pair<String, CommandServer>> liveCommandServerPairs = new ArrayList<Pair<String, CommandServer>>();
	private List<Pair<String, CommandServer>> notLiveCommandServerPairs = new ArrayList<Pair<String, CommandServer>>();
	public static long TryReconnectingInterval = 60000;
	private int currentIndex = 0;
	private TryReconnectingTimer tryReconnectingTimer = new TryReconnectingTimer();
	
	public Pair<String, CommandServer> getCommandServer() throws  CommandException{
		if(liveCommandServerPairs.isEmpty()){
			establishConnectionsToServers();
		}
		if(!notLiveCommandServerPairs.isEmpty()){
			if(tryReconnectingTimer.process()){
				tryReconnecting();
			}
		}
		if(currentIndex >= liveCommandServerPairs.size()) currentIndex = 0;
		return liveCommandServerPairs.get(currentIndex++);
	}
	
	private void tryReconnecting() {
		List<Pair<String, CommandServer>> commandServerPairs = new ArrayList<Pair<String, CommandServer>>(notLiveCommandServerPairs.size());
		List<Pair<String, CommandServer>> toRemoveCommandServerPairs = new ArrayList<Pair<String, CommandServer>>(notLiveCommandServerPairs.size());
		for(Pair<String, CommandServer> notLiveCommandServerPair: notLiveCommandServerPairs)
		{
			Pair<String, CommandServer> commandServerPair = connect(notLiveCommandServerPair.getFirst());
			if(commandServerPair != null){
				commandServerPairs.add(commandServerPair);
				toRemoveCommandServerPairs.add(notLiveCommandServerPair);
			}
		}
		if(!commandServerPairs.isEmpty()){
			liveCommandServerPairs.addAll(commandServerPairs);
			notLiveCommandServerPairs.removeAll(toRemoveCommandServerPairs);
		}
	}
	
	private void establishConnectionsToServers() throws CommandException {
		List<String> commandServerAddressesColonPortNumbers = getCommandServerAddressesColonPortNumbers();
		List<Pair<String, CommandServer>> commandServerPairs = new ArrayList<Pair<String, CommandServer>>(commandServerAddressesColonPortNumbers.size());
		for(String ipAddressCollonPortNumber: commandServerAddressesColonPortNumbers)
		{
			Pair<String, CommandServer> commandServerPair = connect(ipAddressCollonPortNumber);
			if(commandServerPair != null){
				commandServerPairs.add(commandServerPair);
			}
		}
		if(commandServerPairs.isEmpty()){
			throw new CommandException("Could not connect to any of the servers:" + commandServerAddressesColonPortNumbers);
		}
		liveCommandServerPairs = commandServerPairs;
	}

	@Override
	public void setServerNotLive(Pair<String, CommandServer> serverPair) {
		liveCommandServerPairs.remove(serverPair);
		notLiveCommandServerPairs.add(serverPair);
	}

	class TryReconnectingTimer extends Timer{

		public TryReconnectingTimer() {
			super();
		}

		@Override
		protected long getIntervalTime() {
			return TryReconnectingInterval;
		}

	}
}
