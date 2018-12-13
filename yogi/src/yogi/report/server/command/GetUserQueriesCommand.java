package yogi.report.server.command;

import java.util.List;

import yogi.remote.client.app.CommandAdapter;
import yogi.report.server.QueryFile;
import yogi.report.server.config.UserQueryAssistant;


public class GetUserQueriesCommand extends CommandAdapter<List<QueryFile>>{

	private static final long serialVersionUID = 6483943472028152507L;
	
	public GetUserQueriesCommand(String userId) {
		super(userId);
	}

	@Override
	public List<QueryFile> execute() {
		return   UserQueryAssistant.get().getQueries(getUserId());
	}

}
