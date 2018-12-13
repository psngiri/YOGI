package yogi.report.server.command;

import java.util.List;

import yogi.remote.CommandException;
import yogi.remote.client.app.CommandAdapter;
import yogi.remote.client.app.MultiServerCommandExecutor;
import yogi.report.app.ReportProperties;
import yogi.report.server.ReportServerImpl;
import yogi.server.gui.applicationpermissions.command.GetAuthorizedModulesCommand;

public class GetReportsCommand extends CommandAdapter<List<String>> {

	private static final long serialVersionUID = -6849748273507429406L;
		
	public GetReportsCommand(String userId) {
		super(userId);
	}

	@Override
	public List<String> execute() {
		try {
			return MultiServerCommandExecutor.get().execute(ReportProperties.UserAuthorizationServerName, new GetAuthorizedModulesCommand(ReportProperties.ApplicationName, ReportServerImpl.get().getReports(), getUserId()));
		} catch (CommandException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
