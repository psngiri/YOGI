package yogi.server.gui.user.preferences.io.db;

import yogi.server.gui.record.io.db.RecordDbScanner;
import yogi.server.gui.user.preferences.UserPreferences;
import yogi.server.gui.user.preferences.UserPreferencesCreator;
import yogi.server.gui.user.preferences.UserPreferencesData;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;

public class UserPreferencesDbScanner extends RecordDbScanner<UserPreferencesKey,UserPreferencesData,UserPreferences, UserPreferencesCreator> {

	@Override
	protected UserPreferencesCreator getCreator() {
		return new UserPreferencesCreator();
	}

	@Override
	protected Class<UserPreferencesData> getRecordDataClass() {
		return UserPreferencesData.class;
	}

}
