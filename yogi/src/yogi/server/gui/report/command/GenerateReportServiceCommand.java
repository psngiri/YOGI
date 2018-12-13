package yogi.server.gui.report.command;

import java.util.List;

import yogi.remote.client.app.CommandAdapter;
import yogi.report.server.Query;
import yogi.report.server.command.GenerateReportDataRowsCommand;


public class GenerateReportServiceCommand extends BaseGenerateReportServiceCommand<List<Object[]>> {

	private static final long serialVersionUID = -6849748273507429406L;
	
	public GenerateReportServiceCommand(String name, String queryUserId, String partitionCode, String[] filterValues, String[] dataSets) {
		super(name, queryUserId, partitionCode, filterValues, dataSets);
	}

	@Override
	protected CommandAdapter<List<Object[]>> getCommand(Query updatedQuery,	String queryUserId) {
		return new GenerateReportDataRowsCommand(updatedQuery,queryUserId, true) ;
	}

}