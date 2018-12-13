package examples.server.fare.farekey.io.db;

import yogi.base.Selector;
import yogi.base.io.db.DbRecord;
/**
 * @author Vikram Vadavala
 *
 */

public class FareKeyDbRecordSelector implements Selector<DbRecord> {

	public boolean select(DbRecord dbRecord) {
		return true;
	}

}
