package yogi.server.gui.report.command;

import java.util.List;

import yogi.cluster.ClusterFactory;
import yogi.server.gui.record.RecordCreator;
import yogi.server.gui.record.command.RecordClusterCommand;
import yogi.server.gui.record.key.KeyManager;
import yogi.server.gui.report.Report;
import yogi.server.gui.report.ReportCreator;
import yogi.server.gui.report.ReportData;
import yogi.server.gui.report.ReportFactory;
import yogi.server.gui.report.key.ReportKey;
import yogi.server.gui.report.key.ReportKeyManager;

public class ReportClusterCommand extends RecordClusterCommand<ReportKey, ReportData, Report> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReportClusterCommand(Report report) {
		super(report);
	}

	public ReportClusterCommand(List<Report> objects) {
		super(objects);
	}

	@Override
	protected Class<ReportData> getRecordDataClass() {
		return ReportData.class;
	}

	@Override
	protected RecordCreator<ReportKey, ReportData, Report> getRecordCreator() {
		return new ReportCreator();
	}

	@Override
	protected KeyManager<ReportKey> getKeyManager() {
		return ReportKeyManager.get();
	}

	@Override
	protected ClusterFactory<Report> getFactory() {
		return ReportFactory.get();
	}

}
