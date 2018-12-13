package yogi.server.gui.user.preferences.key;

import yogi.server.gui.partition.Partition;
import yogi.server.gui.record.key.KeyImpl;
import yogi.server.gui.user.User;

public class UserPreferencesKeyImpl extends KeyImpl<UserPreferencesKey> implements UserPreferencesKey {

	protected UserPreferencesKeyImpl(User user, String idName, long createDate, Partition partition) {
		super(user, idName, createDate, partition);
	}
	
}
