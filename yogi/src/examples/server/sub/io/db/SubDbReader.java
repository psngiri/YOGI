package examples.server.sub.io.db;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.resource.db.DbResource;
import examples.server.sub.Sub;
import examples.server.sub.SubFactory;
import examples.server.sub.SubValidator;

public class SubDbReader extends DefaultDbRecordReader<Sub> {
	public static boolean RUN = true;
	
	public SubDbReader(DbResource resource) {
		super(resource);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new DefaultRecordProcessor<Sub, DbRecord>(new SubValidator(), new SubDbScanner(), SubFactory.get(), new SubDbRecordSelector()));
	}

	public SubDbReader(Collection<DbRecord> subs) {
		super(subs);
		setup();
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
}
