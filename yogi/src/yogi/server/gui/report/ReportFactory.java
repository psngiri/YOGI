package yogi.server.gui.report;

import yogi.server.gui.record.RecordFactory;
import yogi.server.gui.report.key.ReportKey;

public class ReportFactory extends RecordFactory<ReportKey, ReportData, Report> {
	private static ReportFactory itsInstance = new ReportFactory(ReportManager.get());

	protected ReportFactory(ReportManager manager) {
		super(manager, true);
	}

	public static ReportFactory get() {
		return itsInstance;
	}

	@Override
	protected void add(Report object) {
		super.add(object);
	}
}
