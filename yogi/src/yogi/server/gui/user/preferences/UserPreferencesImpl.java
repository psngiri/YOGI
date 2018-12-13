package yogi.server.gui.user.preferences;

import yogi.server.action.Action;
import yogi.server.gui.record.RecordImpl;
import yogi.server.gui.user.User;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;

public class UserPreferencesImpl extends RecordImpl<UserPreferencesKey,UserPreferencesData,UserPreferences> implements UserPreferences {

	protected UserPreferencesImpl(UserPreferencesKey key,long timeStamp, String description, String comments, UserPreferencesData userPreferencesData, Action action, User modifiedByUser) {
		super(key, timeStamp, description, comments,userPreferencesData, action, modifiedByUser);
	}
	
}