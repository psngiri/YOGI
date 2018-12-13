package yogi.server.gui.record.key;

import yogi.server.base.purge.Purgeable;
import yogi.server.gui.partition.Partition;
import yogi.server.gui.user.User;
/**
 * @author Vikram Vadavala
 *
 */

public interface Key extends Purgeable {
	User getUser();
	String getUserId();
	String getIdName();
	long getCreateDate();
	Partition getPartition();
}
