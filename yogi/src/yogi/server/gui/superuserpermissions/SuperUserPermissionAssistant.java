package yogi.server.gui.superuserpermissions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import yogi.base.FactoryListener;
import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.remote.CommandException;
import yogi.remote.client.app.MultiServerCommandExecutor;
import yogi.server.gui.superuserpermissions.command.CheckSuperUserPermissionsCommand;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.user.preferences.UserPreferencesData;
import yogi.server.gui.user.preferences.UserPreferencesManager;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyManager;
import yogi.server.gui.usergroup.UserGroup;
import yogi.server.gui.usergroup.UserGroupManager;
import yogi.server.gui.userinfo.UserInfo;
import yogi.server.gui.userinfo.UserInfoFactory;

public class SuperUserPermissionAssistant extends RelationshipAssistant<User> {
	
	public static String SuperUserServer = "ProfileServer";
	
	private static SuperUserPermissionAssistant itsInstance = new SuperUserPermissionAssistant();
	private static OneToOneSimpleRelationship<User, User> superUserRel = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(User.class, User.class, "SuperUser");
	private Map<String, String> superUserMap = new HashMap<String, String>();
	
	public SuperUserPermissionAssistant() {
		super();	
		UserInfoFactory.get().addFactoryListener(new FactoryListener<UserInfo>(){
			
			@Override
			public void add(UserInfo object) {
			}

			@Override
			public boolean delete(UserInfo userInfo) {
				return true;
			}
			@Override
			public boolean clearAll() {
				clear();
				return true;
			}
		});
	}
	
	private void clear() {
		superUserMap.clear();
	}
	
	public static SuperUserPermissionAssistant get() {
		return itsInstance;
	}
		
	public boolean setSuperUser(User loginUser, User user) {
		if(user != null) {
			checkPermissions(loginUser, user);
		}
		this.setRelationship(loginUser, superUserRel, user);
		return true;
	}
		
	public boolean isSuperUser(String loginUserId, String userId) {
		String tmpUserId = superUserMap.get(loginUserId);
		if(tmpUserId != null && tmpUserId.equals(userId)) return true;
		try {
			Boolean rtnValue  = MultiServerCommandExecutor.get().execute(SuperUserServer, new CheckSuperUserPermissionsCommand(loginUserId, userId));
			if(rtnValue){
				superUserMap.put(loginUserId, userId);
				return true;
			}
		} catch (CommandException e) {
			throw new RuntimeException(e);
		}
		return false;
	}
	
	public String getSuperUserId(String loginUserId) {
		User loginUser = UserManager.get().getUser(loginUserId);
		User user = this.getSuperUser(loginUser);
		if(user == null) return null;
		return user.getId();
	}
	
	public User getSuperUser(User loginUser) {		
		return this.getRelationship(loginUser, superUserRel);
	}
	
	public boolean isValidSuperUser(User superUser, UserPreferencesData userPreferencesData) {
		User user = getSuperUserFor(userPreferencesData);
		if(user == null) return true;
		return checkPermissions(superUser, user);
	}

	private boolean checkPermissions(User superUser, User user) {
		Set<UserGroup> userGroups = new HashSet<UserGroup>(UserGroupManager.get().getUserGroups(user));
		List<SuperUserPermission> permissions = SuperUserPermissionManager.get().getSuperUserPermissions(superUser);
		for(SuperUserPermission permission : permissions){
			if(permission.isUserGroup()) {
				if(userGroups.contains(permission.getUserGroup())) return true;
			} else {
				if(permission.getUser() == user) return true;
			}
		}
		throw new RuntimeException("UnAuthorized SuperUser access : " + superUser.getId() + " cannot be a superuser for " + user.getId());
	}
	
	public void checkAuthorizedSuperUser(User superUser, User user) {
		User selectedUser = getUser(superUser);
		if(user != selectedUser) throw new RuntimeException("UnAuthorized SuperUser access : " + superUser.getId() + " is not a authorized superuser for " + user.getId());		
	}
	
	public Set<User> getUsers(User superUser) {
		Set<User> rtnVal = new HashSet<User>();
		List<SuperUserPermission> permissions = SuperUserPermissionManager.get().getSuperUserPermissions(superUser);
		for(SuperUserPermission permission : permissions){
			if(permission.isUserGroup()) {
				rtnVal.addAll(permission.getUserGroup().getUsers());
			} else {
				rtnVal.add(permission.getUser());
			}
		}
		return rtnVal;		
	}
	
	public User getUser(User superUser) {
		UserPreferencesKey key = UserPreferencesKeyManager.get().getKey(superUser);
		UserPreferencesData userPreferencesData = UserPreferencesManager.get().getLatestRecord(key).getData();
		User superUserFor = getSuperUserFor(userPreferencesData);
		if(superUserFor != null) return superUserFor;
		return superUser;
	}

	private User getSuperUserFor(UserPreferencesData userPreferencesData) {
		String userId = userPreferencesData.getProperty("superUserFor");
		if(userId != null && !userId.isEmpty()) return UserManager.get().getUser(userId);
		return null;
	}
}