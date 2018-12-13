package yogi.server.gui.user.preferences;

import yogi.server.gui.record.RecordCreator;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;

public class UserPreferencesCreator extends RecordCreator<UserPreferencesKey, UserPreferencesData, UserPreferences> {
	@Override
	public UserPreferences create() {
		return new UserPreferencesImpl(getKey(), getTimeStamp(), getDescription(), getComments(), getData(), getAction(), getModifiedByUser());
		
	}
}