package yogi.versioning.version.io;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.DefaultStringRecordReader;

import yogi.versioning.version.Version;
import yogi.versioning.version.VersionFactory;
import yogi.versioning.version.VersionValidator;

public class VersionReader extends DefaultStringRecordReader<Version> {
	public static boolean RUN = true;
	public VersionReader(String fileName) {
		super(fileName);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new DefaultRecordProcessor<Version, String>(new VersionValidator(), new VersionScanner(), VersionFactory.get(), new VersionRecordSelector()));
	}

	public VersionReader(Collection<String> versions) {
		super(versions);
		setup();
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
}
