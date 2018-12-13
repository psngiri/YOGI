package yogi.server.gui.record.base.app.incremental.command;

import yogi.cluster.ClusterCommand;
import yogi.server.gui.record.base.app.incremental.BaseIncrementalWriterLoopModule;

public class IncrementalWriterSinkClusterCommand extends ClusterCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4468687693332182368L;

	private long endExecutionTime;
	
	public IncrementalWriterSinkClusterCommand(long endExecutionTime) {
		super();
		this.endExecutionTime = endExecutionTime;
	}

	@Override
	protected Boolean clusterExecute() {
		BaseIncrementalWriterLoopModule.resetLoopChecker(endExecutionTime);
		return true;
	}

}
