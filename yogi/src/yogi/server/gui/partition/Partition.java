package yogi.server.gui.partition;

import yogi.base.indexing.Index;
import yogi.base.relationship.RelationshipObject;

public interface Partition extends RelationshipObject, Index<String>, Comparable<Partition>
{
	String getPartitionCode();
}