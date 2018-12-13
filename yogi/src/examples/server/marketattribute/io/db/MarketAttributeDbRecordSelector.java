package examples.server.marketattribute.io.db;

import yogi.base.Selector;
import yogi.base.io.db.DbRecord;


public class MarketAttributeDbRecordSelector implements Selector<DbRecord> {

	public boolean select(DbRecord dbRecord) {
		return true;
	}

}
