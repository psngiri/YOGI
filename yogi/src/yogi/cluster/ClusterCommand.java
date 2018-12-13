package yogi.cluster;

import yogi.base.app.ApplicationProperties;
import yogi.remote.client.app.CommandAdapter;

public abstract class ClusterCommand extends CommandAdapter<Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1354005723849675710L;
	public static String clusterUserId = null;
	private long timestamp = System.currentTimeMillis();
	
	public ClusterCommand() {
		super(clusterUserId);
	}
	
	@Override
	public Boolean execute() {
		if(timestamp < getValidationTimestamp()) return true;
		return clusterExecute();
	}

	protected long getValidationTimestamp() {
		return ApplicationProperties.StartTimeStamp;
	}

	protected abstract Boolean clusterExecute();
}
