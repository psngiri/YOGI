package yogi.server.gui.superuserpermissions.command;

import yogi.remote.client.app.CommandAdapter;
import yogi.server.gui.superuserpermissions.SuperUserPermissionAssistant;

public class CheckSuperUserPermissionsCommand extends CommandAdapter<Boolean> {
	
	private static final long serialVersionUID = 1L;
	
	private String loginUserId;

	public CheckSuperUserPermissionsCommand(String loginUserId, String userId) {
		super(userId);
		this.loginUserId = loginUserId;
	}

	@Override
	public Boolean execute() {
		return this.getUserId().equals(SuperUserPermissionAssistant.get().getSuperUserId(loginUserId));
	}
	
}
