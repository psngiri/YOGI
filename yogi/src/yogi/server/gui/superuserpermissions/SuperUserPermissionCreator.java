package yogi.server.gui.superuserpermissions;

import yogi.base.Creator;
import yogi.server.gui.user.User;
import yogi.server.gui.usergroup.UserGroup;

public class SuperUserPermissionCreator implements Creator<SuperUserPermission> {
	
	private User superUser;
	private User user;
	private UserGroup userGroup;
	
	public SuperUserPermission create() {
		if(user != null){
			return new SuperUserPermissionUserImpl(superUser, user);
		} else{
			return new SuperUserPermissionUserGroupImpl(superUser, userGroup);			
		}
	}

	public User getSuperUser() {
		return superUser;
	}

	public void setSuperUser(User superUser) {
		this.superUser = superUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	@Override
	public String toString() {
		return superUser + "/" + user + "/" + userGroup;
	}
		
}