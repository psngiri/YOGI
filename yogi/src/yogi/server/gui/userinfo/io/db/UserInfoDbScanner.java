package yogi.server.gui.userinfo.io.db;

import yogi.base.app.ErrorReporter;
import yogi.base.io.Scanner;
import yogi.base.io.db.DbRecord;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.userinfo.UserInfo;
import yogi.server.gui.userinfo.UserInfoCreator;

public class UserInfoDbScanner implements Scanner<UserInfo, DbRecord> {
	private UserInfoCreator creator = new UserInfoCreator();

	public UserInfoCreator scan(DbRecord dbRecord) {
		try {
			creator.setUser(UserManager.get().getUser(dbRecord.getString(1)));
			creator.setFirstName(dbRecord.getString(2));
			creator.setLastName(dbRecord.getString(3));
		} catch (Exception e) {
			ErrorReporter.get().error(dbRecord, e);
		}
		return creator;
	}
}
