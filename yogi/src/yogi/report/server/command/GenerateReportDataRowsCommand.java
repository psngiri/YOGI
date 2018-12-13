package yogi.report.server.command;

import java.util.ArrayList;
import java.util.List;

import yogi.remote.client.app.CommandAdapter;
import yogi.report.server.Query;
import yogi.report.server.ReportData;
import yogi.report.server.ReportServerImpl;
import yogi.report.server.ReportTableData;


public class GenerateReportDataRowsCommand extends CommandAdapter<List<Object[]>> {

	private static final long serialVersionUID = -6849748273507429406L;
	private Query query;
	private int queryId = -1;
	private boolean excludeHeader = false; 

	public GenerateReportDataRowsCommand(Query query, String userId) {
		super(userId);
		this.query = query;
	}
	public GenerateReportDataRowsCommand(Query query, String userId, boolean excludeHeader) {
		super(userId);
		this.query = query;
		this.excludeHeader = excludeHeader;
	}
	
	public GenerateReportDataRowsCommand(Query query, int queryId, String userId) {
		this(query, userId);
		this.queryId = queryId;
	}
	
	@Override
	public List<Object[]> execute() {
		ReportTableData tableData = ReportServerImpl.get().generateReport(query, queryId, getUserId());
		List<String> header = tableData.getHeader();
		List<ReportData> rows = tableData.getRows();
		List<Object[]> rtnValue = new ArrayList<Object[]>();
		if(!excludeHeader) rtnValue.add(header.toArray());
		for(ReportData row: rows){
			rtnValue.add(row.getValues());
		}
		return rtnValue;
	}


}
