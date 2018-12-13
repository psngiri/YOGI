package yogi.server.gui.applicationinfo.io.db;

import yogi.base.Selector;
import yogi.base.io.db.DbRecord;


public class ApplicationInfoDbRecordSelector implements Selector<DbRecord> {

	public boolean select(DbRecord dbRecord) {
		return true;
	}

}
