package yogi.report.server.command;

import yogi.remote.client.app.CommandAdapter;
import yogi.report.server.ReportServerImpl;


public class CloseAllReportsCommand extends CommandAdapter<Boolean> {

	private static final long serialVersionUID = 4416999955474970536L;

	public CloseAllReportsCommand(String userId) {
		super(userId);
	}

	public Boolean execute() {
		return  ReportServerImpl.get().closeReports();
	}
}