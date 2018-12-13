package yogi.server.gui.partition;

import yogi.base.relationship.RelationshipObjectImpl;

public class PartitionImpl extends RelationshipObjectImpl<Partition> implements Partition
{
	private String partitionCode;

	protected PartitionImpl(String partitionCode)
	{
		super();
		this.partitionCode = partitionCode;
	}

	public String getName()
	{
		return partitionCode;
	}

	public String getPartitionCode()
	{
		return partitionCode;
	}

	public String getIndex()
	{
		return getPartitionCode();
	}

	public int compareTo(Partition o)
	{
		if(this == o) return 0;
		return partitionCode.compareTo(o.getPartitionCode());
	}

	@Override
	public String toString()
	{
		return partitionCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj == PartitionManager.ANY||this == PartitionManager.ANY) return true;
		return this == obj;
	}

}
