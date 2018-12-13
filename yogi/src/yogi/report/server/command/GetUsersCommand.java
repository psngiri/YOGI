package yogi.report.server.command;

import java.util.List;

import yogi.remote.client.app.CommandAdapter;
import yogi.report.server.config.UserQueryAssistant;


public class GetUsersCommand extends CommandAdapter<List<String>>{

	private static final long serialVersionUID = 6483943472028152507L;
	
	public GetUsersCommand() {
		super(null);
	}

	@Override
	public List<String> execute() {
		return   UserQueryAssistant.get().getQueryUsers();
	}

}
