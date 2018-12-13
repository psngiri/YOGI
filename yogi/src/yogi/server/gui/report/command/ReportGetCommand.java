package yogi.server.gui.report.command;

import yogi.server.gui.command.TypeGetCommand;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.record.key.KeyManager;
import yogi.server.gui.report.Report;
import yogi.server.gui.report.ReportData;
import yogi.server.gui.report.ReportManager;
import yogi.server.gui.report.key.ReportKey;
import yogi.server.gui.report.key.ReportKeyManager;


public class ReportGetCommand extends TypeGetCommand<ReportKey,ReportData, Report> {

	private static final long serialVersionUID = -6849748273507429406L;

	public ReportGetCommand(String name, String selectedUserId, String partitionCode) {
		super(name, selectedUserId);
		this.partitionCode = partitionCode;
	}

	@Override
	public KeyManager<ReportKey> getKeyManager() {
		return ReportKeyManager.get();
	}

	@Override
	public RecordManager<ReportKey,ReportData, Report> getRecordManager() {
		return ReportManager.get();
	}

}