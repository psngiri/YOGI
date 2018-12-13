package yogi.report.server.command;

import yogi.remote.client.app.CommandAdapter;
import yogi.report.server.ReportServerImpl;


public class CloseReportCommand extends CommandAdapter<Boolean> {

	private static final long serialVersionUID = -6849748273507429406L;
	int queryId;

	public CloseReportCommand(int queryId,String userId) {
		super(userId);
		this.queryId = queryId;
	}
	
	public Boolean execute() {
		return  ReportServerImpl.get().closeReport(queryId, getUserId());
	}


}
