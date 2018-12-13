package yogi.server.gui.superuserpermissions;

import yogi.base.relationship.RelationshipObject;
import yogi.server.gui.user.User;
import yogi.server.gui.usergroup.UserGroup;

public interface SuperUserPermission extends RelationshipObject {
	User getSuperUser();
	UserGroup getUserGroup();
	User getUser();
	boolean isUserGroup();
}
