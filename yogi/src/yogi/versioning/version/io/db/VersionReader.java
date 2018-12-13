package yogi.versioning.version.io.db;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.resource.db.DbResource;
import yogi.versioning.version.Version;
import yogi.versioning.version.VersionFactory;
import yogi.versioning.version.VersionValidator;

public class VersionReader extends DefaultDbRecordReader<Version> {
	public static boolean RUN = true;
	public VersionReader(DbResource resource) {
		super(resource);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new DefaultRecordProcessor<Version, DbRecord>(new VersionValidator(), new VersionScanner(), VersionFactory.get(), new VersionRecordSelector()));
	}

	public VersionReader(Collection<DbRecord> versions) {
		super(versions);
		setup();
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
}
