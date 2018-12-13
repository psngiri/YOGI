package yogi.server.gui.report.key;

import yogi.base.Factory;

public class ReportKeyFactory extends Factory<ReportKey> {
	private static ReportKeyFactory itsInstance = new ReportKeyFactory(ReportKeyManager.get());

	protected ReportKeyFactory(ReportKeyManager manager) {
		super(manager);
	}

	public static ReportKeyFactory get() {
		return itsInstance;
	}
}
