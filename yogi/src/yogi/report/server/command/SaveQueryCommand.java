package yogi.report.server.command;

import yogi.remote.client.app.CommandAdapter;
import yogi.report.server.UserQuery;
import yogi.report.server.config.UserQueryAssistant;


public class SaveQueryCommand extends CommandAdapter<Boolean>{

	private static final long serialVersionUID = 6483943472028152507L;
	private UserQuery userQuery;
	
	public SaveQueryCommand(UserQuery userQuery) {
		super(null);
		this.userQuery = userQuery;
	}

	@Override
	public Boolean execute() {
		return   UserQueryAssistant.get().saveQuery(userQuery);
	}

}
