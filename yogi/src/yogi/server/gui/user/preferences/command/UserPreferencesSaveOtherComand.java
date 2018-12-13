package yogi.server.gui.user.preferences.command;

import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.user.preferences.UserPreferencesData;
import yogi.server.gui.user.preferences.UserPreferencesFieldsAssistant;

public class UserPreferencesSaveOtherComand extends UserPreferencesSaveCommand {
	private String saveUserId;
	public UserPreferencesSaveOtherComand(String userId, UserPreferencesData userPreferencesData, String saveUserId) {
		super(userId, userPreferencesData);
		this.saveUserId = saveUserId;
	}

	@Override
	protected String getSaveUserId() {
		String userId = super.getUserId();
		User user = UserManager.get().getUser(userId);
		if(!UserPreferencesFieldsAssistant.get().isUserAuthorised(user)){
			throw new RuntimeException("");
		}
		return saveUserId;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4664178983844521840L;

}
