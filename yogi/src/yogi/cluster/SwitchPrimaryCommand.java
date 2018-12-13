package yogi.cluster;


public class SwitchPrimaryCommand extends ClusterCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5685178952224752968L;
	private Boolean isPirmary;
	public SwitchPrimaryCommand(Boolean object) {
		super();
		isPirmary = object;
	}

	@Override
	protected Boolean clusterExecute() {
		ClusterManager.setPrimary(String.valueOf(isPirmary));
		return true;
	}

}
