package yogi.server.gui.partition;

import yogi.base.Creator;

public class PartitionCreator implements Creator<Partition>
{
	private String partitionCode;

	public Partition create()
	{
		return new PartitionImpl(partitionCode);
	}

	public String getPartitionCode()
	{
		return partitionCode;
	}

	public void setPartitionCode(String partitionCode)
	{
		this.partitionCode = partitionCode;
	}
}