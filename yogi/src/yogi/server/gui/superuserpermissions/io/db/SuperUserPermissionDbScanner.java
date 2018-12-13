package yogi.server.gui.superuserpermissions.io.db;

import yogi.base.app.ErrorReporter;
import yogi.base.io.Scanner;
import yogi.base.io.db.DbRecord;
import yogi.server.gui.superuserpermissions.SuperUserPermission;
import yogi.server.gui.superuserpermissions.SuperUserPermissionCreator;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.usergroup.UserGroupManager;

public class SuperUserPermissionDbScanner implements Scanner<SuperUserPermission, DbRecord> {
	
	private SuperUserPermissionCreator creator = new SuperUserPermissionCreator();

	public SuperUserPermissionCreator scan(DbRecord dbRecord) {
		try {
			User superUser = UserManager.get().getUser(dbRecord.getString(1));
			creator.setSuperUser(superUser);
			String userEntity = dbRecord.getString(2);
			String groupValue = dbRecord.getString(3);
			boolean isGroup = false;
			if(groupValue != null && groupValue.trim().equalsIgnoreCase("Y")) isGroup = true;
			if(isGroup) {
				creator.setUserGroup(UserGroupManager.get().getUserGroup(userEntity));
				creator.setUser(null);
			} else {
				creator.setUser(UserManager.get().getUser(userEntity));
				creator.setUserGroup(null);
			}
		} catch (Exception e) {
			ErrorReporter.get().error(dbRecord, e);
		}
		return creator;
	}
}
