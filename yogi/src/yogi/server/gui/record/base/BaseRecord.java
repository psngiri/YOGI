package yogi.server.gui.record.base;

import yogi.base.relationship.RelationshipObject;
import yogi.server.action.Action;
import yogi.server.base.purge.Purgeable;
import yogi.server.gui.user.User;


/**
 * @author Vikram Vadavala
 *
 */

public interface BaseRecord<K extends RelationshipObject> extends RelationshipObject, Purgeable{
	K getKey();
	Action getAction();
	long getTimeStamp();
	User getModifiedByUser();
}
