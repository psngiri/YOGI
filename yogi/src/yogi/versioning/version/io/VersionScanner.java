package yogi.versioning.version.io;

import yogi.base.io.Scanner;

import yogi.versioning.version.Version;
import yogi.versioning.version.VersionCreator;

public class VersionScanner implements Scanner<Version, String> {
	private VersionCreator creator = new VersionCreator();

	public VersionCreator scan(String record) {
		return creator;
	}
}
