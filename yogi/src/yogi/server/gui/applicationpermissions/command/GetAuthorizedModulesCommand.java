package yogi.server.gui.applicationpermissions.command;

import java.util.ArrayList;
import java.util.List;

import yogi.remote.client.app.CommandAdapter;
import yogi.server.gui.applicationpermissions.ApplicationPermissionsAssistant;
import yogi.server.gui.user.UserManager;

public class GetAuthorizedModulesCommand extends CommandAdapter<List<String>> {

	private static final long serialVersionUID = -4474870172160553166L;
	private String appName=null;
	private List<String> moduleNames=null;
	
	public GetAuthorizedModulesCommand(String appName, List<String> moduleNames, String userId) {
		super(userId);
		this.appName=appName;
		this.moduleNames=moduleNames;
	}
	
	@Override
	public List<String> execute() {
		List<String> authorizedModules= new ArrayList<String>();
		for(String moduleName : moduleNames){
			if(ApplicationPermissionsAssistant.get().isAuthorized(appName, moduleName, UserManager.get().getUser(getUserId()))) authorizedModules.add(moduleName);
		}
		return authorizedModules;
	}

}
