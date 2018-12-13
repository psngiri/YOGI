package yogi.versioning.version.io;

import yogi.base.Selector;
import yogi.base.io.FactoryWriter;
import yogi.base.io.FileWriter;
import yogi.versioning.version.Version;
import yogi.versioning.version.VersionFactory;

public class VersionWriter extends FactoryWriter<Version> {
	public static boolean RUN = true;
	public VersionWriter(String fileName, Selector<? super Version> selector) {
		super(VersionFactory.get(), new FileWriter<Version>(fileName, new VersionFormatter()),
				selector);
	}

	public VersionWriter(String fileName) {
		this(fileName, null);
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}
}
