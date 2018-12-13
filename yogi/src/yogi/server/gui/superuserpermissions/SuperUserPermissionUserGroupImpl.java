package yogi.server.gui.superuserpermissions;

import yogi.base.relationship.RelationshipObjectImpl;
import yogi.server.gui.user.User;
import yogi.server.gui.usergroup.UserGroup;


public class SuperUserPermissionUserGroupImpl extends RelationshipObjectImpl<SuperUserPermission> implements SuperUserPermission {
	
	private User superUser;
	private UserGroup userGroup;

	protected SuperUserPermissionUserGroupImpl(User superUser, UserGroup userGroup) {
		super();
		this.superUser = superUser;
		this.userGroup = userGroup;
	}
	
	@Override
	public User getSuperUser() {
		return superUser;
	}

	@Override
	public User getUser() {
		return null;
	}

	@Override
	public UserGroup getUserGroup() {
		return userGroup;
	}

	public String getName() {
		return superUser.getName() + "/" +  userGroup.getName();
	}

	@Override
	public String toString() {
		return  getName();
	}

	@Override
	public boolean isUserGroup() {
		return true;
	}

}