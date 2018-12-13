package yogi.remote.forward;

import yogi.remote.CommandException;
import yogi.remote.client.app.CommandAdapter;
import yogi.remote.client.app.MultiServerCommandExecutor;
import yogi.remote.gson.GsonCommand;

public class ForwardCommand extends CommandAdapter<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3068470887791976680L;
	private String serverType;
	private GsonCommand gsonCommand;
	public ForwardCommand(String serverType, GsonCommand gsonCommand) {
		super(gsonCommand.getUserId());
		this.serverType = serverType;
		this.gsonCommand = gsonCommand;
	}

	@Override
	public String execute() {
		try {
			CommandAdapter<String> myCommand = gsonCommand;
			if(ForwardManager.get().forward(serverType)){
				myCommand = new ForwardCommand(serverType, gsonCommand);
			}
			return  MultiServerCommandExecutor.get().execute(serverType,myCommand);
		} catch (CommandException e) {
			e.printStackTrace();
			return "{\"ErrorMessage\":\""+e.getMessage()+"\"}";
		}
	}

	String getServerType() {
		return serverType;
	}

	GsonCommand getGsonCommand() {
		return gsonCommand;
	}

}
