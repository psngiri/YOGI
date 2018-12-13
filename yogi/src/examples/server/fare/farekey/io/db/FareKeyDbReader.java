package examples.server.fare.farekey.io.db;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.resource.db.DbResource;

import examples.server.fare.farekey.FareKey;
import examples.server.fare.farekey.FareKeyFactory;
import examples.server.fare.farekey.FareKeyValidator;
/**
 * @author Vikram Vadavala
 *
 */

public class FareKeyDbReader extends DefaultDbRecordReader<FareKey> {
	public static boolean RUN = true;
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
	
	public FareKeyDbReader(DbResource resource) {
		super(resource);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new DefaultRecordProcessor<FareKey, DbRecord>(new FareKeyValidator(), new FareKeyDbScanner(), FareKeyFactory.get(), new FareKeyDbRecordSelector()));
	}

	public FareKeyDbReader(Collection<DbRecord> fareKeys) {
		super(fareKeys);
		setup();
	}
	
}
