package yogi.server.gui.user.preferences;

import yogi.base.Factory;

public class UserPreferencesFactory extends Factory<UserPreferences> {
	private static UserPreferencesFactory itsInstance = new UserPreferencesFactory(UserPreferencesManager.get());

	protected UserPreferencesFactory(UserPreferencesManager manager) {
		super(manager);
	}

	public static UserPreferencesFactory get() {
		return itsInstance;
	}
}
