package yogi.server.gui.user.preferences.command;

import yogi.base.app.Executor;
import yogi.remote.client.app.CommandAdapter;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.user.preferences.UserPreferences;
import yogi.server.gui.user.preferences.UserPreferencesData;
import yogi.server.gui.user.preferences.UserPreferencesManager;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyManager;


public class UserPreferencesResetSuperUserCommand extends CommandAdapter<Boolean> {

	private static final long serialVersionUID = 2532961303607208160L;

	public UserPreferencesResetSuperUserCommand(String userId) {
		super(userId);
	}

	@Override
	public Boolean execute() {		
		User user = UserManager.get().getUser(getUserId());
		UserPreferencesKey key = UserPreferencesKeyManager.get().getKey(user);
		if(key == null) return false;
		UserPreferences userPreferences = UserPreferencesManager.get().getLatestRecord(key);
		if(userPreferences == null) return false;
		UserPreferencesData data = userPreferences.getData();
		Object property = data.getProperty("superUserFor");
		if(property == null || ((String)property).isEmpty()) return false;
		data.setProperty("superUserFor", "");
		UserPreferencesSaveCommand command = new UserPreferencesSaveCommand(getUserId(), data);
		Executor.get().execute(command);	
		return true;
	}
}