package yogi.server.gui.userinfo;

import yogi.base.Factory;

public class UserInfoFactory extends Factory<UserInfo> {
	private static UserInfoFactory itsInstance = new UserInfoFactory(UserInfoManager.get());

	protected UserInfoFactory(UserInfoManager manager) {
		super(manager);
	}

	public static UserInfoFactory get() {
		return itsInstance;
	}
}
