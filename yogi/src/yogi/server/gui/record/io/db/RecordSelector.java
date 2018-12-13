package yogi.server.gui.record.io.db;

import yogi.base.Selector;
import yogi.base.io.db.DbRecord;


public class RecordSelector implements Selector<DbRecord> {

	public boolean select(DbRecord dbRecord) {
		return true;
	}

}
