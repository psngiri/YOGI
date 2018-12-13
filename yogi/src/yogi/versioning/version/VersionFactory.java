package yogi.versioning.version;

import yogi.base.Factory;

public class VersionFactory extends Factory<Version> {
	private static VersionFactory versionFactory = new VersionFactory(VersionManager.get());

	protected VersionFactory(VersionManager manager) {
		super(manager);
	}

	public static VersionFactory get() {
		return versionFactory;
	}
}
