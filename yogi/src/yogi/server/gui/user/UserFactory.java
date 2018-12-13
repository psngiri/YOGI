package yogi.server.gui.user;

import yogi.base.Factory;

public class UserFactory extends Factory<User> {
	private static UserFactory itsInstance = new UserFactory(UserManager.get());

	protected UserFactory(UserManager manager) {
		super(manager);
	}

	public static UserFactory get() {
		return itsInstance;
	}
}
