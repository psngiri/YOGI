package yogi.cluster;

import java.util.Map;

import yogi.remote.client.app.CommandExecutor;

public class ImmediateClusterController extends DelayedClusterController {
	public static int clusterEveryseconds = 5;
	public ImmediateClusterController(Map<String, CommandExecutor> serverCommandExecutors) {
		super(serverCommandExecutors, clusterEveryseconds);
	}

	public void process(ClusterCommand command) {
		processResendCommands(command);
	}
}