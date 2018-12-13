package yogi.mapreduce;

import yogi.remote.client.app.CommandAdapter;

public abstract class MapReduceCommand<R, C extends CommandAdapter<R>> extends CommandAdapter<R> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8917965143142474989L;
	private C command;
	public MapReduceCommand(C command) {
		super(command.getUserId());
		this.command = command;
	}

	@Override
	public R execute() {
		return MapReduceManager.get().execute(command, this.getReducer());
	}

	public abstract Reducer<R> getReducer();
}
