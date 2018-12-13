package yogi.versioning.version.io;

import yogi.base.io.Formatter;
import yogi.versioning.version.Version;

public class VersionFormatter implements Formatter<Version> {
	public String format(Version version) {
		return version.toString();
	}
}
