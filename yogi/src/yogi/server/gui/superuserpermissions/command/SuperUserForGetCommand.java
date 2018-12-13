package yogi.server.gui.superuserpermissions.command;

import yogi.remote.client.app.CommandAdapter;
import yogi.server.gui.superuserpermissions.SuperUserPermissionAssistant;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;

public class SuperUserForGetCommand extends CommandAdapter<String> {

	private static final long serialVersionUID = 7166513806743562911L;

	public SuperUserForGetCommand(String userId) {
		super(userId);
	}

	@Override
	public String execute() {
		String rtnVal = "";
		User user = SuperUserPermissionAssistant.get().getSuperUser(UserManager.get().getUser(getUserId()));
		if(user != null) rtnVal = user.getId();		
		return rtnVal;
	}
}