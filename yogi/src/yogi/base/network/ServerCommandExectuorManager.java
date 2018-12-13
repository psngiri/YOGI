package yogi.base.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yogi.base.network.command.AddToNetworkCommand;
import yogi.base.network.command.BroadCastNetworkToPeersCommand;
import yogi.base.network.command.BroadCastRemoveFromNetworkToPeersCommand;
import yogi.cluster.ClusterManager;
import yogi.remote.CommandException;
import yogi.remote.client.app.CommandExecutor;

public class ServerCommandExectuorManager {
	private Map<String, CommandExecutor> serverCommandExecutors = new HashMap<String, CommandExecutor>();
	private static ServerCommandExectuorManager itsInstance = new ServerCommandExectuorManager();
	public static String ServerAddressIPColonPortNumber = "";
	
	public static ServerCommandExectuorManager get(){
		return itsInstance;
	}
	
	public Map<String, CommandExecutor> getServerCommandExecutors() {
		return serverCommandExecutors;
	}
	
	public List<String> getServersInNetwork(){
		ArrayList<String> rtnValue = new ArrayList<String>(serverCommandExecutors.keySet());
		rtnValue.add(ServerAddressIPColonPortNumber);
		return rtnValue;
	}
	
	public static void addToNetwork(String peerIpColonPortNumber){
		CommandExecutor commandExecutor = new CommandExecutor(peerIpColonPortNumber, peerIpColonPortNumber);
		try {
			commandExecutor.execute(new AddToNetworkCommand(ServerAddressIPColonPortNumber));
			addServersToCluster(new String[]{peerIpColonPortNumber}, false);
		} catch (CommandException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeServersFromCluster(String hostColonPortNumbers){
		removeServersFromCluster(hostColonPortNumbers, true);
	}
	
	public static void removeServersFromCluster(String hostColonPortNumbers, boolean broadcast){
		if(broadcast){
			ClusterManager.get().process(new BroadCastRemoveFromNetworkToPeersCommand(hostColonPortNumbers));
			ClusterManager.get().flush();
		}
		if(hostColonPortNumbers.trim().equalsIgnoreCase("All")){
			ServerCommandExectuorManager.get().serverCommandExecutors.clear();
		}else{
			String[] split = hostColonPortNumbers.split(",");
			for(String token: split)
			{
				if(token.equals(ServerAddressIPColonPortNumber)){
					ServerCommandExectuorManager.get().serverCommandExecutors.clear();
				}else{
					ServerCommandExectuorManager.get().serverCommandExecutors.remove(token);
				}
			}
		}
		System.out.println(ServerCommandExectuorManager.get().serverCommandExecutors.keySet());
	}
	
	public static void addServersToCluster(String hostColonPortNumbers){
		String[] split = hostColonPortNumbers.split(",");
		addServersToCluster(split, true);
	}

	public static void addServersToCluster(String[] split, boolean broadcast) {
		for(String token: split)
		{
			if(token.equals(ServerAddressIPColonPortNumber)||ServerCommandExectuorManager.get().serverCommandExecutors.containsKey(token)) continue;
			ServerCommandExectuorManager.get().serverCommandExecutors.put(token, new CommandExecutor(token, token));
		}
		if(broadcast){
			String[] ServerIpColonPortNumbers = ServerCommandExectuorManager.get().serverCommandExecutors.keySet().toArray(new String[0]);
			ClusterManager.get().process(new BroadCastNetworkToPeersCommand(ServerIpColonPortNumbers));
		}
		System.out.println(ServerCommandExectuorManager.get().serverCommandExecutors.keySet());
	}
	
}
