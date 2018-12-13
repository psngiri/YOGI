package yogi.server.gui.user.preferences.command;

import java.util.List;

import yogi.remote.client.app.CommandAdapter;
import yogi.server.gui.user.preferences.UserPreferencesFieldsAssistant;

public class UserPreferencesPermissionsGetCommand extends CommandAdapter<List<String>> {

	private static final long serialVersionUID = -6849748273507429406L;
	
	public UserPreferencesPermissionsGetCommand(String userId) {
		super(userId);
	}

	@Override
	public List<String> execute() {
		return UserPreferencesFieldsAssistant.get().getDisabledFields(getUserId());
	}
}