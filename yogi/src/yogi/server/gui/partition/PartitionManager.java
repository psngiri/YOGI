package yogi.server.gui.partition;

import yogi.base.ObjectNotFoundException;
import yogi.base.app.ErrorReporter;
import yogi.base.relationship.index.IndexedManager;

public class PartitionManager extends IndexedManager<Partition, String>
{
	private static PartitionManager itsInstance = new PartitionManager();
	private static PartitionCreator partitionCreator = new PartitionCreator();
	public static Partition ANY = new PartitionImpl(" ");//Do not remove extra space

	protected PartitionManager()
	{
		super();
	}

	public static PartitionManager get()
	{
		return itsInstance;
	}

	public Partition getPartition(String partitionCode)
	{
		if (null == partitionCode) {
			return ANY;
		}
		partitionCode = partitionCode.trim();
		if (partitionCode.isEmpty()) {
			return ANY;
		}
		try {
			return this.getObject(partitionCode);
		} catch (ObjectNotFoundException e) {
			return createPartition(partitionCode);
		}
	}

	private synchronized Partition createPartition(String partitionCode)
	{
		try {
			return this.getObject(partitionCode);
		} catch (ObjectNotFoundException e) {
			ErrorReporter.get().finest("Creating Partition ", partitionCode);
			partitionCreator.setPartitionCode(partitionCode);
			return PartitionFactory.get().create(partitionCreator);
		}
	}
}