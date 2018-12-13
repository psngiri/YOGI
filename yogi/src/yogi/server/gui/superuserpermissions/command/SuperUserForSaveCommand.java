package yogi.server.gui.superuserpermissions.command;

import yogi.remote.client.app.CommandAdapter;
import yogi.server.gui.superuserpermissions.SuperUserPermissionAssistant;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;

public class SuperUserForSaveCommand extends CommandAdapter<Boolean> {

	private static final long serialVersionUID = 7166513806743562911L;
	
	private String selectedUserId;

	public SuperUserForSaveCommand(String userId, String selectedUserId) {
		super(userId);
		this.selectedUserId = selectedUserId;
	}

	@Override
	public Boolean execute() {
		User user = UserManager.get().getUser(getUserId());
		User selectedUser = UserManager.get().getUser(selectedUserId);
		return SuperUserPermissionAssistant.get().setSuperUser(user, selectedUser);
	}
}