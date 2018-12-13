package yogi.server.gui.usergroup.io.db;

import yogi.base.Selector;
import yogi.base.io.db.DbRecord;


public class UserGroupDbRecordSelector implements Selector<DbRecord> {

	public boolean select(DbRecord dbRecord) {
		return true;
	}

}
