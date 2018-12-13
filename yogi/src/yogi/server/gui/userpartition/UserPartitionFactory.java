package yogi.server.gui.userpartition;

import yogi.base.Factory;

public class UserPartitionFactory extends Factory<UserPartition> {
	private static UserPartitionFactory itsInstance = new UserPartitionFactory(UserPartitionManager.get());

	protected UserPartitionFactory(UserPartitionManager manager) {
		super(manager);
	}

	public static UserPartitionFactory get() {
		return itsInstance;
	}
}
