package yogi.server.gui.partition;

import yogi.base.validation.ValidatorAdapter;

public class PartitionValidator extends ValidatorAdapter<Partition>
{
	@Override
	public boolean validate(Partition partition)
	{
		if (partition.getPartitionCode().length() < 2) {
			return false;
		}
		return true;
	}
}
