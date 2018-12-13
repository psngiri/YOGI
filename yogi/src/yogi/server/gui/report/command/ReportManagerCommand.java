package yogi.server.gui.report.command;

import yogi.report.app.ReportProperties;
import yogi.server.gui.applicationpermissions.ApplicationPermissionsAssistant;
import yogi.server.gui.command.TypeManagerCommand;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.record.key.KeyManager;
import yogi.server.gui.report.Report;
import yogi.server.gui.report.ReportData;
import yogi.server.gui.report.ReportManager;
import yogi.server.gui.report.key.ReportKey;
import yogi.server.gui.report.key.ReportKeyManager;
import yogi.server.gui.user.User;


public class ReportManagerCommand extends TypeManagerCommand<ReportKey,ReportData, Report> {

	private static final long serialVersionUID = -6849748273507429406L;

	public ReportManagerCommand(String selectedUserId) {
		super(selectedUserId);
	}
	
	@Override
	public KeyManager<ReportKey> getKeyManager() {
		return ReportKeyManager.get();
	}

	@Override
	public RecordManager<ReportKey,ReportData, Report> getRecordManager() {
		return  ReportManager.get();
	}

	public boolean isAuthorized(User user, Report latestRecord) {
		String reportName = latestRecord.getData().getQuery().getReportName();
		return ApplicationPermissionsAssistant.get().isAuthorized(ReportProperties.ApplicationName, reportName.trim(), user);
	}
	
}