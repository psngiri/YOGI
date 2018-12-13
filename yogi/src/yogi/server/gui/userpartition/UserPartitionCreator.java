package yogi.server.gui.userpartition;

import yogi.base.Creator;
import yogi.server.gui.partition.Partition;
import yogi.server.gui.user.User;

public class UserPartitionCreator implements Creator<UserPartition> {
	private User user;
	private Partition partition;
	
	public UserPartition create() {
		return new UserPartitionImpl(user,partition);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Partition getPartition() {
		return partition;
	}

	public void setPartition(Partition partition) {
		this.partition = partition;
	}
}
