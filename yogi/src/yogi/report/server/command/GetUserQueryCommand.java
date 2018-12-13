package yogi.report.server.command;

import yogi.remote.client.app.CommandAdapter;
import yogi.report.server.Query;
import yogi.report.server.config.UserQueryAssistant;


public class GetUserQueryCommand extends CommandAdapter<Query>{

	private static final long serialVersionUID = 6483943472028152507L;
	private String userName;
	private String queryName;
	
	public GetUserQueryCommand(String userName, String queryName, String userId) {
		super(userId);
		this.userName = userName;
		this.queryName = queryName;
	}

	@Override
	public Query execute() {
		return   UserQueryAssistant.get().getQuery(userName, queryName);
	}

}
