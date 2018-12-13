package yogi.server.gui.user.preferences.level.command;

import yogi.remote.client.app.CommandAdapter;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.user.preferences.level.UserPreferencesLevel;
import yogi.server.gui.user.preferences.level.UserPreferencesLevelAssistant;


public class UserPreferencesLevelGetCommand extends CommandAdapter<UserPreferencesLevel> {

	private static final long serialVersionUID = -5623808760900983179L;

	public UserPreferencesLevelGetCommand(String userId) {
		super(userId);
	}

	@Override
	public UserPreferencesLevel execute() {		
		User user = UserManager.get().getUser(getUserId());
		return UserPreferencesLevelAssistant.get().getUserPreferencesLevel(user);
	}
}