package yogi.server.gui.report.command;

import yogi.server.gui.command.TypeGetUsersCommand;
import yogi.server.gui.record.key.KeyManager;
import yogi.server.gui.report.key.ReportKey;
import yogi.server.gui.report.key.ReportKeyManager;


public class ReportGetUsersCommand extends TypeGetUsersCommand<ReportKey> {

	private static final long serialVersionUID = -3338090347106604543L;

	public ReportGetUsersCommand(String loginUserId) {
		super(loginUserId);
	}

	@Override
	public KeyManager<ReportKey> getKeyManager() {
		return ReportKeyManager.get();
	}

}