package yogi.report.server.command;

import yogi.base.util.Pair;
import yogi.paging.command.SubmitTableDataCommand;
import yogi.report.server.Query;
import yogi.report.server.ReportData;
import yogi.report.server.ReportTableData;


public class PagingReportDataCommand extends SubmitTableDataCommand<ReportData> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 548374695723177038L;
	public PagingReportDataCommand(ReportTableData reportTableData, int tableDataSetId, String userId,Pair<Query, Long> queryTimestamp) {
		super(reportTableData.getRows(), new ReportDataMapper(reportTableData,queryTimestamp), tableDataSetId,userId);
	}

}
