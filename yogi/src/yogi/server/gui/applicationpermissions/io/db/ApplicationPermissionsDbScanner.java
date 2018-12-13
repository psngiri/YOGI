package yogi.server.gui.applicationpermissions.io.db;

import yogi.base.app.ErrorReporter;
import yogi.base.io.Scanner;
import yogi.base.io.db.DbRecord;
import yogi.server.gui.applicationinfo.ApplicationInfo;
import yogi.server.gui.applicationinfo.ApplicationInfoManager;
import yogi.server.gui.applicationpermissions.ApplicationPermissions;
import yogi.server.gui.applicationpermissions.ApplicationPermissionsCreator;
import yogi.server.gui.moduleinfo.ModuleInfoManager;
import yogi.server.gui.usergroup.UserGroupManager;

public class ApplicationPermissionsDbScanner implements Scanner<ApplicationPermissions, DbRecord> {
	private ApplicationPermissionsCreator creator = new ApplicationPermissionsCreator();

	public ApplicationPermissionsCreator scan(DbRecord dbRecord) {
		try {
			ApplicationInfo applicationInfo = ApplicationInfoManager.get().getApplicationInfo(dbRecord.getString(1));
			creator.setApplicationInfo(applicationInfo);
			creator.setModuleInfo(ModuleInfoManager.get().getModule(applicationInfo, dbRecord.getString(2)));
			creator.setUserGroup(UserGroupManager.get().getUserGroup(dbRecord.getString(3)));
			creator.setAuthorized(dbRecord.getString(4).trim().equalsIgnoreCase("F") ? false : true);
		} catch (Exception e) {
			ErrorReporter.get().error(dbRecord, e);
		}
		return creator;
	}
}
