package yogi.server.gui.userinfo;

import yogi.base.validation.ValidatorAdapter;

public class UserInfoValidator extends ValidatorAdapter<UserInfo> {
	@Override
	public boolean validate(UserInfo userInfo) {
		if(userInfo.getFirstName().trim().isEmpty()){
			return false;
		}
		if(userInfo.getLastName().trim().isEmpty()){
			return false;
		}
		return true;
	}
}
