package yogi.server.gui.applicationpermissions;

import yogi.base.relationship.RelationshipObject;
import yogi.server.gui.applicationinfo.ApplicationInfo;
import yogi.server.gui.moduleinfo.ModuleInfo;
import yogi.server.gui.usergroup.UserGroup;

public interface ApplicationPermissions extends RelationshipObject {
	ApplicationInfo getApplicationInfo();
	ModuleInfo getModule();
	UserGroup getUserGroup();
	boolean isAuthorized();
}
