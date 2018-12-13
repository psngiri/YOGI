package examples.server.fare.io.db;

import yogi.base.Selector;
import yogi.base.io.db.DbRecord;


public class FareDbRecordSelector implements Selector<DbRecord> {

	public boolean select(DbRecord dbRecord) {
		return true;
	}

}
