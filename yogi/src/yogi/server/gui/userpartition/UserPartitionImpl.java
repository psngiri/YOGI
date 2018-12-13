package yogi.server.gui.userpartition;

import yogi.base.relationship.RelationshipObjectImpl;
import yogi.server.gui.partition.Partition;
import yogi.server.gui.user.User;

public class UserPartitionImpl extends RelationshipObjectImpl<UserPartition> implements UserPartition {
	
	User user;
	Partition partition;
	
	public UserPartitionImpl(User user, Partition partition) {
		super();
		this.user = user;
		this.partition = partition;
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public Partition getPartition() {
		return partition;
	}

	@Override
	public String getName() {
		return user.getName();
	}

	@Override
	public User getIndex() {
		return user;
	}


}
