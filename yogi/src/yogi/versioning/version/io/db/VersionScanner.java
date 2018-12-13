package yogi.versioning.version.io.db;

import yogi.base.io.Scanner;
import yogi.base.io.db.DbRecord;

import yogi.versioning.version.Version;
import yogi.versioning.version.VersionCreator;

public class VersionScanner implements Scanner<Version, DbRecord> {
	private VersionCreator creator = new VersionCreator();

	public VersionCreator scan(DbRecord dbRecord) {
		return creator;
	}
}
