package yogi.versioning.transaction;

import yogi.versioning.version.Version;

public class Transaction {
	private Version version;

	protected Transaction(Version version) {
		super();
		this.version = version;
	}

	public Version getVersion() {
		return version;
	}
	
	public static Transaction getTransaction()
	{
		return null;
	}
}
