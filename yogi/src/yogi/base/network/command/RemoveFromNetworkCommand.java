package yogi.base.network.command;

import yogi.base.network.ServerCommandExectuorManager;
import yogi.cluster.ClusterCommand;
import yogi.remote.client.app.CommandAdapter;

public class RemoveFromNetworkCommand extends CommandAdapter<Boolean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2857936755965323622L;
	private String ipColonPortNumber;
	
	public RemoveFromNetworkCommand(String ipColonPortNumber) {
		super(ClusterCommand.clusterUserId);
		this.ipColonPortNumber = ipColonPortNumber;
	}

	@Override
	public Boolean execute() {
		ServerCommandExectuorManager.removeServersFromCluster(ipColonPortNumber);
		return true;
	}

}
