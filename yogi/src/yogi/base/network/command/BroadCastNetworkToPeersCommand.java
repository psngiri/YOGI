package yogi.base.network.command;

import yogi.base.network.ServerCommandExectuorManager;
import yogi.cluster.ClusterCommand;

public class BroadCastNetworkToPeersCommand extends ClusterCommand {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2857936755965323622L;
	private String[] ipColonPortNumbers;
	
	public BroadCastNetworkToPeersCommand(String[] ipColonPortNumbers) {
		super();
		this.ipColonPortNumbers = ipColonPortNumbers;
	}


	@Override
	protected Boolean clusterExecute() {
		ServerCommandExectuorManager.addServersToCluster(ipColonPortNumbers, false);
		return true;
	}

}
