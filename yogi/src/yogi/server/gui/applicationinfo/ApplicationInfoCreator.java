package yogi.server.gui.applicationinfo;

import yogi.base.Creator;
import yogi.server.gui.partition.Partition;

public class ApplicationInfoCreator implements Creator<ApplicationInfo>
{
	private String applicationName;
	private Partition partition;

	public ApplicationInfo create()
	{
		return new ApplicationInfoImpl(applicationName, partition);
	}

	public String getAppName()
	{
		return applicationName;
	}

	public void setAppName(String applicationName)
	{
		this.applicationName = applicationName;
	}

	public Partition getPartition() {
		return partition;
	}

	public void setPartition(Partition partition) {
		this.partition = partition;
	}
	
	@Override
	public String toString() {
		return applicationName + "/" + partition.getPartitionCode();
	}
	
}