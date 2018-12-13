package yogi.server.gui.applicationpermissions;

import yogi.base.Creator;
import yogi.server.gui.applicationinfo.ApplicationInfo;
import yogi.server.gui.moduleinfo.ModuleInfo;
import yogi.server.gui.usergroup.UserGroup;

public class ApplicationPermissionsCreator implements Creator<ApplicationPermissions> {
	
	private ApplicationInfo applicationInfo;
	private ModuleInfo moduleInfo;
	private UserGroup userGroup;
	private boolean authorized;
	
	public ApplicationPermissions create() {
		return new ApplicationPermissionsImpl(applicationInfo, moduleInfo, userGroup, authorized);
	}

	public ApplicationInfo getApplicationInfo() {
		return applicationInfo;
	}

	public ModuleInfo getModuleInfo() {
		return moduleInfo;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public boolean isAuthorized() {
		return authorized;
	}

	public void setApplicationInfo(ApplicationInfo applicationName) {
		this.applicationInfo = applicationName;
	}

	public void setModuleInfo(ModuleInfo moduleInfo) {
		this.moduleInfo = moduleInfo;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}

	@Override
	public String toString() {
		return applicationInfo+"/"+moduleInfo+"/"+userGroup+"/"+authorized;
	}
	
	
}
