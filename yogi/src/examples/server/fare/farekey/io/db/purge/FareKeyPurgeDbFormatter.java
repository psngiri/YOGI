package examples.server.fare.farekey.io.db.purge;

import yogi.base.io.db.DbRecord;
import yogi.base.io.db.ObjectDbRecord;
import yogi.server.base.purge.io.db.PurgeDbFormatter;
import examples.server.fare.farekey.FareKey;

public class FareKeyPurgeDbFormatter extends PurgeDbFormatter<FareKey> {
	private ObjectDbRecord dbRecord = new ObjectDbRecord(1);

	protected FareKeyPurgeDbFormatter() {
		super();
	}

	public DbRecord format(FareKey key) {
		dbRecord.clear();
		dbRecord.setObject(0, key.getId());
		return dbRecord;
	}

}
