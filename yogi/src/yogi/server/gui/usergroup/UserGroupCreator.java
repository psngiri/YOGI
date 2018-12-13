package yogi.server.gui.usergroup;

import yogi.base.Creator;

public class UserGroupCreator implements Creator<UserGroup> {
	
	private String groupName;
	
	public UserGroup create() {
		return new UserGroupImpl(groupName);
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public String toString() {
		return groupName;
	}
	
	
	
}
