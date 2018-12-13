package examples.server.fare.io.db.purge;

import yogi.base.io.db.DbRecord;
import yogi.base.io.db.ObjectDbRecord;
import yogi.server.base.purge.io.db.PurgeDbFormatter;

import examples.server.fare.Fare;

public class FarePurgeDbFormatter extends PurgeDbFormatter<Fare> {
	private ObjectDbRecord dbRecord = new ObjectDbRecord(2);

	protected FarePurgeDbFormatter() {
		super();
	}

	public DbRecord format(Fare record) {
		dbRecord.clear();
		dbRecord.setObject(0, record.getKey().getId());
		dbRecord.setObject(1, record.getVersion().getSubNumber());
		return dbRecord;
	}

}
