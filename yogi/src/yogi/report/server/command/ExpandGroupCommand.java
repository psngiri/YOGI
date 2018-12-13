package yogi.report.server.command;

import java.util.List;

import yogi.remote.client.app.CommandAdapter;
import yogi.report.server.ReportDataIndex;
import yogi.report.server.ReportDataRows;
import yogi.report.server.ReportServerImpl;

public class ExpandGroupCommand extends CommandAdapter<ReportDataRows>{
	
	private static final long serialVersionUID = -8506090706849219914L;
	private int queryId;
	private int groupIndex;
	private List<ReportDataIndex> indexes;
	
	public ExpandGroupCommand(int queryId, int groupIndex,
			List<ReportDataIndex> indexes, String userId) {
		super(userId);
		this.queryId = queryId;
		this.groupIndex = groupIndex;
		this.indexes = indexes;
	}

	@Override
	public ReportDataRows execute() {
		
		ReportDataRows rtnValue = ReportServerImpl.get().expandGroup(queryId, groupIndex, indexes,getUserId());
		return rtnValue;
		
	}

}
