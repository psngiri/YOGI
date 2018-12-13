package yogi.server.gui.user.preferences.key.io.db;

import yogi.server.gui.record.key.io.db.KeyDbScanner;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyCreator;

public class UserPreferencesKeyDbScanner extends KeyDbScanner<UserPreferencesKey, UserPreferencesKeyCreator> {

	@Override
	protected UserPreferencesKeyCreator getCreator() {
		return new UserPreferencesKeyCreator();
	}

}
