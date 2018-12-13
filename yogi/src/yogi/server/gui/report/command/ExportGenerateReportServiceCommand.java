package yogi.server.gui.report.command;

import java.util.List;

import yogi.remote.client.app.CommandAdapter;
import yogi.report.server.Query;
import yogi.report.server.command.ExportReportCommand;


public class ExportGenerateReportServiceCommand extends BaseGenerateReportServiceCommand<List<String[]>> {

	private static final long serialVersionUID = -6849748273507429406L;
	
	public ExportGenerateReportServiceCommand(String name, String queryUserId, String partitionCode, String[] filterValues, String[] dataSets) {
		super(name, queryUserId, partitionCode, filterValues, dataSets);
	}

	@Override
	protected CommandAdapter<List<String[]>> getCommand(Query updatedQuery,	String queryUserId) {
		return new ExportReportCommand(updatedQuery,queryUserId) ;
	}

}