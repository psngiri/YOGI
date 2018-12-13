package yogi.base.network.command;

import java.util.List;

import yogi.base.network.ServerCommandExectuorManager;
import yogi.cluster.ClusterCommand;
import yogi.remote.client.app.CommandAdapter;

public class ServersInNetworkCommand extends CommandAdapter<List<String>> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2857936755965323622L;
	
	public ServersInNetworkCommand() {
		super(ClusterCommand.clusterUserId);
	}

	@Override
	public List<String> execute() {
		List<String> serversInNetwork = ServerCommandExectuorManager.get().getServersInNetwork();
		return serversInNetwork;
	}

}
