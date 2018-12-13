package yogi.server.gui.usergroup;

import yogi.base.validation.ValidatorAdapter;

public class UserGroupValidator extends ValidatorAdapter<UserGroup> {
	@Override
	public boolean validate(UserGroup userGroup) {
		if(userGroup.getGroupName()==null || userGroup.getGroupName().trim().isEmpty()) return false;
		return true;
	}
}
