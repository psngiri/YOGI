package yogi.server.gui.applicationpermissions;

import yogi.base.relationship.RelationshipObjectImpl;
import yogi.server.gui.applicationinfo.ApplicationInfo;
import yogi.server.gui.moduleinfo.ModuleInfo;
import yogi.server.gui.usergroup.UserGroup;


public class ApplicationPermissionsImpl extends RelationshipObjectImpl<ApplicationPermissions> implements ApplicationPermissions {
	
	private ApplicationInfo applicationInfo;
	private ModuleInfo moduleInfo;
	private UserGroup userGroup;
	private boolean authorized;
	
	protected ApplicationPermissionsImpl(ApplicationInfo applicationName, ModuleInfo moduleInfo, UserGroup userGroup, boolean authorized) {
		super();
		this.applicationInfo = applicationName;
		this.moduleInfo = moduleInfo;
		this.userGroup = userGroup;
		this.authorized = authorized;
	}

	public String getName() {
		return applicationInfo.getName()+"/"+moduleInfo.getName()+"/"+userGroup.getName()+"/"+authorized;
	}

	public ApplicationInfo getApplicationInfo() {
		return applicationInfo;
	}

	@Override
	public ModuleInfo getModule() {
		return moduleInfo;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public boolean isAuthorized() {
		return authorized;
	}

	@Override
	public String toString() {
		return applicationInfo+"/"+moduleInfo.getModuleName()+"/"+userGroup+"/"+authorized;
	}

	

}
