package yogi.report.server.command;

import yogi.remote.client.app.CommandAdapter;
import yogi.report.server.Query;
import yogi.report.server.ReportServerImpl;
import yogi.report.server.ReportTableData;


public class GenerateReportCommand extends CommandAdapter<ReportTableData> {

	private static final long serialVersionUID = -6849748273507429406L;
	private Query query;
	private int queryId = -1;

	public GenerateReportCommand(Query query, String userId) {
		super(userId);
		this.query = query;
	}
	
	public GenerateReportCommand(Query query, int queryId, String userId) {
		this(query, userId);
		this.queryId = queryId;
	}
	
	@Override
	public ReportTableData execute() {
		return ReportServerImpl.get().generateReport(query, queryId, getUserId());
	}


}
