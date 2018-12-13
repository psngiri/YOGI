package yogi.server.gui.userinfo;

import yogi.base.relationship.RelationshipObject;
import yogi.server.gui.user.User;

/**
 * @author Vikram Vadavala
 *
 */
public interface UserInfo extends RelationshipObject,Comparable<UserInfo> {
	User getUser();
	String getFirstName();
	String getLastName();
	String getUserName();
}
