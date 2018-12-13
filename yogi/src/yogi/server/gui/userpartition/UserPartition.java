package yogi.server.gui.userpartition;

import yogi.base.indexing.Index;
import yogi.base.relationship.RelationshipObject;
import yogi.server.gui.partition.Partition;
import yogi.server.gui.user.User;

public interface UserPartition extends RelationshipObject, Index<User>{
	User getUser();
	Partition getPartition();
}
