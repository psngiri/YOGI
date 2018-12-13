package yogi.server.gui.usergroup.io.db;

import java.util.HashSet;
import java.util.Set;

import yogi.base.app.ErrorReporter;
import yogi.base.io.Scanner;
import yogi.base.io.db.DbRecord;
import yogi.base.util.XString;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.usergroup.UserGroup;
import yogi.server.gui.usergroup.UserGroupCreator;
import yogi.server.gui.usergroup.UserGroupManager;

public class UserGroupDbScanner implements Scanner<UserGroup, DbRecord> {
	
	public static String UsersWildChar="%";

	public UserGroupCreator scan(DbRecord dbRecord) {
		try {
			UserGroup userGroup = UserGroupManager.get().getUserGroup(dbRecord.getString(1).trim(),true);
			Set<User> users = getUsers(dbRecord.getString(2));
			userGroup.addUsers(users);
		} catch (Exception e) {
			ErrorReporter.get().error(dbRecord, e);
		}
		return null;
	}
	
	private Set<User> getUsers(String userString){
		String userStringTrimed = userString.trim();
		Set<User> users = new HashSet<User>();
		if(userStringTrimed.endsWith(UsersWildChar)) {
			for(User user : UserManager.get().findAll()){
				if(XString.isLike(user.getId(), userStringTrimed)){
					users.add(user);
				}
			}
		}else{
			users.add(UserManager.get().getUser(userString));
		}	
		return users;
	}
	
	
}
