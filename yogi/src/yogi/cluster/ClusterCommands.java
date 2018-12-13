package yogi.cluster;

import java.util.List;

import yogi.base.app.Executor;
import yogi.remote.client.app.CommandAdapter;

public class ClusterCommands extends CommandAdapter<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1354005723849675710L;
	private List<ClusterCommand> clusterCommands;
	
	public ClusterCommands(List<ClusterCommand> clusterCommands) {
		super(ClusterCommand.clusterUserId);
		this.clusterCommands = clusterCommands;
	}
	
	@Override
	public Integer execute() {
		int i = 0;
		for(ClusterCommand command: clusterCommands)
		{
			Boolean execute = false;
			try {
				execute = Executor.get().execute(command);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(!execute) break;
			i++;
		}
		return i;
	}

}
