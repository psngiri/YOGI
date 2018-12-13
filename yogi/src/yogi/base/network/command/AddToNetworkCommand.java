package yogi.base.network.command;

import yogi.base.network.ServerCommandExectuorManager;
import yogi.cluster.ClusterCommand;
import yogi.remote.client.app.CommandAdapter;

public class AddToNetworkCommand extends CommandAdapter<Boolean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2857936755965323622L;
	private String ipColonPortNumber;
	
	public AddToNetworkCommand(String ipColonPortNumber) {
		super(ClusterCommand.clusterUserId);
		this.ipColonPortNumber = ipColonPortNumber;
	}

	@Override
	public Boolean execute() {
		ServerCommandExectuorManager.addServersToCluster(ipColonPortNumber);
		return true;
	}

}
