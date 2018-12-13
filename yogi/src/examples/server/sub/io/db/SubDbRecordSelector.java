package examples.server.sub.io.db;

import yogi.base.Selector;
import yogi.base.io.db.DbRecord;


public class SubDbRecordSelector implements Selector<DbRecord> {

	public boolean select(DbRecord dbRecord) {
		return true;
	}

}
