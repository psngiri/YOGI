package yogi.server.gui.user.preferences.command;

import yogi.server.gui.command.TypeDeleteCommand;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.user.preferences.UserPreferences;
import yogi.server.gui.user.preferences.UserPreferencesCreator;
import yogi.server.gui.user.preferences.UserPreferencesData;
import yogi.server.gui.user.preferences.UserPreferencesFactory;
import yogi.server.gui.user.preferences.UserPreferencesManager;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyManager;

public class UserPreferencesDeleteCommand extends TypeDeleteCommand<UserPreferencesKey,UserPreferencesData, UserPreferences, UserPreferencesCreator> {
	
	private static final long serialVersionUID = -6231317678168741218L;
	
	@Override
	protected boolean canRecordBeDeleted(UserPreferencesKey key) {
		return UserPreferencesManager.get().getLatestRecord(key)!=null ? true : false;
	}

	public UserPreferencesDeleteCommand(String[] names, String userId, String loginUserId) {
		super(names, userId);
		this.loginUserId = loginUserId;
	}
	
	@Override
	public UserPreferencesKeyManager getKeyManager() {
		return UserPreferencesKeyManager.get();
	}

	@Override
	public UserPreferencesFactory getFactory() {
		return UserPreferencesFactory.get();
	}

	@Override
	protected UserPreferencesCreator getCreator() {
		return new UserPreferencesCreator();
	}

	@Override
	public RecordManager<UserPreferencesKey, UserPreferencesData, UserPreferences> getRecordManager() {
		return  UserPreferencesManager.get();
	}
	
}