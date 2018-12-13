package yogi.server.gui.usergroup;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import yogi.base.relationship.RelationshipAssistant;
import yogi.server.gui.user.User;

public class UserGroupAssistant extends RelationshipAssistant<UserGroup> {
	private static UserGroupAssistant itsInstance = new UserGroupAssistant();

	public static UserGroupAssistant get() {
		return itsInstance;
	}
	
	public Set<User> getUsersInLoginUserGroups(User loginUser){
		List<UserGroup> userGroups = UserGroupManager.get().getUserGroups(loginUser);
		Set<User> users = new HashSet<User>();
		for(UserGroup group : userGroups){
			users.addAll(group.getUsers());
		}
		return users;
	}

	
}
