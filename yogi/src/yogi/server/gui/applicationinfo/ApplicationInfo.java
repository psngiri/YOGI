package yogi.server.gui.applicationinfo;

import yogi.base.indexing.Index;
import yogi.base.relationship.RelationshipObject;
import yogi.server.gui.partition.Partition;

public interface ApplicationInfo extends RelationshipObject, Index<String>, Comparable<ApplicationInfo>
{
	String getAppName();
	Partition getPartition();
}