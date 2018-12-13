package yogi.server.gui.applicationpermissions;

import yogi.base.validation.ValidatorAdapter;
import yogi.server.gui.applicationinfo.ApplicationInfoManager;

public class ApplicationPermissionsValidator extends ValidatorAdapter<ApplicationPermissions> {
	@Override
	public boolean validate(ApplicationPermissions applicationPermissions) {
		if(applicationPermissions.getApplicationInfo() == ApplicationInfoManager.NULL) return false;
		return true;
	}
}
