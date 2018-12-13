package examples.flight.io.db;

import yogi.base.Selector;
import yogi.base.io.db.DbRecord;


public class FlightRecordSelector implements Selector<DbRecord> {

	public boolean select(DbRecord dbRecord) {
		return true;
	}

}
