package yogi.server.gui.user.preferences.key;

import yogi.base.Factory;

public class UserPreferencesKeyFactory extends Factory<UserPreferencesKey> {
	private static UserPreferencesKeyFactory itsInstance = new UserPreferencesKeyFactory(UserPreferencesKeyManager.get());

	protected UserPreferencesKeyFactory(UserPreferencesKeyManager manager) {
		super(manager);
	}

	public static UserPreferencesKeyFactory get() {
		return itsInstance;
	}
}
