package yogi.server.gui.user.preferences.key;

import yogi.server.gui.record.key.KeyCreator;

public class UserPreferencesKeyCreator extends KeyCreator<UserPreferencesKey> {

	@Override
	public UserPreferencesKey create() {
		return new UserPreferencesKeyImpl(getUser(),getIdName(), getCreateDate(),getPartition());
	}
}