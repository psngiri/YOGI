package yogi.server.gui.report.command;

import yogi.server.gui.command.TypeDeleteCommand;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.report.Report;
import yogi.server.gui.report.ReportCreator;
import yogi.server.gui.report.ReportData;
import yogi.server.gui.report.ReportFactory;
import yogi.server.gui.report.ReportManager;
import yogi.server.gui.report.key.ReportKey;
import yogi.server.gui.report.key.ReportKeyManager;

public class ReportDeleteCommand extends TypeDeleteCommand<ReportKey, ReportData,Report, ReportCreator> {

	private static final long serialVersionUID = -3338090347106604543L;

	public ReportDeleteCommand(String[] name, String userId) {
		super(name, userId);
	}

	@Override
	public ReportKeyManager getKeyManager() {
		return ReportKeyManager.get();
	}

	@Override
	public ReportFactory getFactory() {
		return ReportFactory.get();
	}

	@Override
	protected ReportCreator getCreator() {
		return new ReportCreator();
	}

	@Override
	public RecordManager<ReportKey, ReportData, Report> getRecordManager() {
		return ReportManager.get();
	}

}