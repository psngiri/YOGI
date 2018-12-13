package yogi.server.gui.usergroup;

import yogi.base.Factory;

public class UserGroupFactory extends Factory<UserGroup> {
	private static UserGroupFactory itsInstance = new UserGroupFactory(UserGroupManager.get());

	protected UserGroupFactory(UserGroupManager manager) {
		super(manager);
	}

	public static UserGroupFactory get() {
		return itsInstance;
	}
}
