package yogi.server.gui.superuserpermissions;

import yogi.base.validation.ValidatorAdapter;
import yogi.server.gui.user.UserManager;

public class SuperUserPermissionValidator extends ValidatorAdapter<SuperUserPermission> {
	@Override
	public boolean validate(SuperUserPermission superUserPermissions) {
		if(superUserPermissions.getSuperUser() == UserManager.BLANK) return false;
		return true;
	}
}
