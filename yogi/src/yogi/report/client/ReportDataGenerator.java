package yogi.report.client;

import java.util.List;

import yogi.base.util.node.Node;
import yogi.remote.CommandException;
import yogi.remote.client.app.MultiServerCommandExecutor;
import yogi.report.server.Query;
import yogi.report.server.ReportData;
import yogi.report.server.ReportServerImpl;
import yogi.report.server.ReportTableData;
import yogi.report.server.command.ExpandGroupCommand;
import yogi.report.server.command.GenerateReportCommand;

public class ReportDataGenerator {
	ReportServerImpl server = new ReportServerImpl();
	public static boolean UseCommandServer = false;
	private List<String> columnNames;
	private String reportType;
	
	private Query query;
	private int queryId;
	private String userId;

	public ReportDataGenerator(Query query, String userId, String reportType) {
		super();
		this.query = query;
		this.userId = userId;
		this.reportType = reportType;
	}

	public String getUserId() {
		return userId;
	}

	public void generateReportData(int groupIndex, Node<ReportData> node) {
		List<ReportData> items = null;
		if(groupIndex == 0)
		{
			if(UseCommandServer)
			{
				try {
					GenerateReportCommand command = new GenerateReportCommand(query, userId);
					ReportTableData rtnValue =  MultiServerCommandExecutor.get().execute(reportType, command);
					columnNames = rtnValue.getHeader();
					items = rtnValue.getRows();
					queryId = rtnValue.getQueryId();
				} catch (CommandException e) {
					e.printStackTrace();
				}
			}else
			{
				ReportTableData rtnValue = server.generateReport(query, -1, userId);
				columnNames = rtnValue.getHeader();
				items = rtnValue.getRows();
				queryId = rtnValue.getQueryId();
			}
		}else
		{
			ReportData reportData = node.getData();
			if(UseCommandServer)
			{
				try {
					items =  MultiServerCommandExecutor.get().execute(reportType, new ExpandGroupCommand(queryId, groupIndex, reportData.getIndexes(),null)).getRows();
				} catch (CommandException e) {
					e.printStackTrace();
				}
			}else
			{
				items = server.expandGroup(queryId, groupIndex, reportData.getIndexes(),null).getRows();
			}
		}
		for(ReportData item: items)
		{
			new Node<ReportData>(node, item);
		}
	}


	public List<String> getColumnNames() {
		return columnNames;
	}
}
