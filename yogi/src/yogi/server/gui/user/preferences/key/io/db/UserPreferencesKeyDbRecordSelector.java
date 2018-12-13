package yogi.server.gui.user.preferences.key.io.db;

import yogi.base.Selector;
import yogi.base.io.db.DbRecord;


public class UserPreferencesKeyDbRecordSelector implements Selector<DbRecord> {

	public boolean select(DbRecord dbRecord) {
		return true;
	}

}
