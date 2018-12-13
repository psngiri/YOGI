package yogi.server.gui.partition;

import yogi.base.Factory;

public class PartitionFactory extends Factory<Partition>
{
	private static PartitionFactory itsInstance = new PartitionFactory(PartitionManager.get());

	protected PartitionFactory(PartitionManager manager)
	{
		super(manager);
	}

	public static PartitionFactory get()
	{
		return itsInstance;
	}
}
