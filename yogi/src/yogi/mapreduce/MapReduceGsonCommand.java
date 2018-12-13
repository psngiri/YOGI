package yogi.mapreduce;

import yogi.remote.client.app.CommandAdapter;
import yogi.remote.gson.GsonCommand;

public class MapReduceGsonCommand extends CommandAdapter<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8917965143142474989L;
	private String key;
	private String value;
	public MapReduceGsonCommand(String userId, String key, String value) {
		super(userId);
		this.key = key;
		this.value = value;
	}

	@Override
	public String execute() {
		return MapReduceManager.get().execute(new GsonCommand(key, value, this.getUserId()), this.getReducer());
	}

	public Reducer<String> getReducer(){
		return MapReduceManager.getReducer(key);
	}
}
