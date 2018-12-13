package yogi.server.gui.applicationpermissions.command;

import yogi.remote.client.app.CommandAdapter;
import yogi.server.gui.applicationpermissions.ApplicationPermissionsAssistant;
import yogi.server.gui.user.UserManager;

public class IsAuthorizedCommand extends CommandAdapter<Boolean> {

	private static final long serialVersionUID = -4474870172160553166L;
	private String appName=null;
	private String moduleName=null;
	
	public IsAuthorizedCommand(String appName, String moduleName, String userId) {
		super(userId);
		this.appName=appName;
		this.moduleName=moduleName;
	}
	
	@Override
	public Boolean execute() {
		if(null==moduleName) return ApplicationPermissionsAssistant.get().isAuthorized(appName, UserManager.get().getUser(getUserId()));
		return ApplicationPermissionsAssistant.get().isAuthorized(appName, moduleName, UserManager.get().getUser(getUserId()));
	}

}
