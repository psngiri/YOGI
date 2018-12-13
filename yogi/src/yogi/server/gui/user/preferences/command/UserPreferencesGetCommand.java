package yogi.server.gui.user.preferences.command;

import yogi.remote.client.app.CommandAdapter;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.user.preferences.UserPreferencesData;
import yogi.server.gui.user.preferences.UserPreferencesManager;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyManager;


public class UserPreferencesGetCommand extends CommandAdapter<UserPreferencesData> {

	private static final long serialVersionUID = -6849748273507429406L;
	
	public UserPreferencesGetCommand(String userId) {
		super(userId);
	}

	@Override
	public UserPreferencesData execute() {
		
		UserPreferencesKey key = UserPreferencesKeyManager.get().getKey(UserManager.get().getUser(getUserId()));
		if(key == null) return new UserPreferencesData();
		return UserPreferencesManager.get().getLatestRecord(key).getData();
	}
}