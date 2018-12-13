package yogi.mapreduce.command;

import java.util.List;

import yogi.mapreduce.MapReduceCommand;
import yogi.mapreduce.Reducer;
import yogi.mapreduce.reducer.ListReducer;
import yogi.remote.client.app.CommandAdapter;

public class ListMapReduceCommand<T, C extends CommandAdapter<List<T>>> extends MapReduceCommand<List<T>, C> {

	private static final long serialVersionUID = 4381201699946849545L;

	public ListMapReduceCommand(C command) {
		super(command);
	}

	@Override
	public Reducer<List<T>> getReducer() {
		return new ListReducer<T>();
	}

}
