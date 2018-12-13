package yogi.server.gui.userinfo.io.db;

import yogi.base.Selector;
import yogi.base.io.db.DbRecord;


public class UserInfoDbRecordSelector implements Selector<DbRecord> {

	public boolean select(DbRecord dbRecord) {
		return true;
	}

}
