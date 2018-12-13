package yogi.report.server.command;

import yogi.base.util.Pair;
import yogi.remote.CommandException;
import yogi.remote.client.app.CommandAdapter;
import yogi.remote.client.app.MultiServerCommandExecutor;
import yogi.report.server.Query;
import yogi.report.server.ReportServerImpl;
import yogi.report.server.ReportTableData;

public class GenerateReportPagingCommand extends CommandAdapter<Integer> {

	private static final long serialVersionUID = -6849748273507429406L;
	private Query query;
	private int tableDataSetId = -1;
	private long timestamp=0L;

	public GenerateReportPagingCommand(Query query, String userId) {
		super(userId);
		this.query = query;
	}
	
	public GenerateReportPagingCommand(Query query, int tableDataSetId, String userId) {
		this(query,userId);
		this.tableDataSetId = tableDataSetId;
	}
	
	public GenerateReportPagingCommand(Query query, int tableDataSetId, String userId, long timestamp) {
		this(query, tableDataSetId, userId);
		this.timestamp = timestamp;
	}
	
	@Override
	public Integer execute() {
		if(query.isGroupReport()) throw new RuntimeException("Paging cant handle Group Reports");
		if(timestamp<=0L) timestamp = System.currentTimeMillis();
		ReportTableData reportData = ReportServerImpl.get().generateReport(query, -1, getUserId());
		try {
			tableDataSetId = MultiServerCommandExecutor.get().execute("Paging", new PagingReportDataCommand(reportData, tableDataSetId,getUserId(),new Pair<Query, Long>(query, timestamp)));
		} catch (CommandException e) {
			throw new RuntimeException(e);
		}
		return tableDataSetId;
	}


}
