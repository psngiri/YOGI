package yogi.server.gui.superuserpermissions;

import yogi.base.relationship.RelationshipObjectImpl;
import yogi.server.gui.user.User;
import yogi.server.gui.usergroup.UserGroup;


public class SuperUserPermissionUserImpl extends RelationshipObjectImpl<SuperUserPermission> implements SuperUserPermission {
	
	private User superUser;
	private User user;

	protected SuperUserPermissionUserImpl(User superUser, User user) {
		super();
		this.superUser = superUser;
		this.user = user;
	}
	
	@Override
	public User getSuperUser() {
		return superUser;
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public UserGroup getUserGroup() {
		return null;
	}

	public String getName() {
		return superUser.getName() + "/" + user.getName();
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public boolean isUserGroup() {
		return false;
	}

}