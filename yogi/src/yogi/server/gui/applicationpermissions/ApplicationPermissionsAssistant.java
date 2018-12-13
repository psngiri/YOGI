package yogi.server.gui.applicationpermissions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import yogi.base.FactoryListener;
import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.server.gui.applicationinfo.ApplicationInfo;
import yogi.server.gui.applicationinfo.ApplicationInfoManager;
import yogi.server.gui.moduleinfo.ModuleInfo;
import yogi.server.gui.moduleinfo.ModuleInfoManager;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.usergroup.UserGroup;
import yogi.server.gui.usergroup.UserGroupFactory;
import yogi.server.gui.usergroup.UserGroupManager;
import yogi.server.gui.userinfo.UserInfo;
import yogi.server.gui.userinfo.UserInfoFactory;

@SuppressWarnings("rawtypes")
public class ApplicationPermissionsAssistant extends RelationshipAssistant<User> {
	private static ApplicationPermissionsAssistant itsInstance = new ApplicationPermissionsAssistant();
	private static OneToOneSimpleRelationship<User, HashSet> userAppAuthorizedRel = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(User.class, HashSet.class, "AuthorizedModules");
	private static OneToOneSimpleRelationship<User, HashSet> userAppUnAuthorizedRel = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(User.class, HashSet.class, "UnAuthorizedModules");

	public static ApplicationPermissionsAssistant get() {
		return itsInstance;
	}
	
	public ApplicationPermissionsAssistant() {
		super();
		
		UserInfoFactory.get().addFactoryListener(new FactoryListener<UserInfo>(){
			
			@Override
			public void add(UserInfo object) {
				
			}

			@Override
			public boolean delete(UserInfo userInfo) {
				clear(userInfo.getUser());
				return true;
			}

			@Override
			public boolean clearAll() {
				clear();
				return true;
			}
			
		});
		
		UserGroupFactory.get().addFactoryListener(new FactoryListener<UserGroup>(){

			@Override
			public void add(UserGroup userGroup) {
				clearUserGroup(userGroup);
			}

			@Override
			public boolean delete(UserGroup userGroup) {
				clearUserGroup(userGroup);
				return true;
			}

			@Override
			public boolean clearAll() {
				clear();
				return true;
			}
			
		});
		
		ApplicationPermissionsFactory.get().addFactoryListener(new FactoryListener<ApplicationPermissions>(){

			@Override
			public void add(ApplicationPermissions applicationPermissions) {
				clearUserGroup(applicationPermissions);
			}

			@Override
			public boolean delete(ApplicationPermissions applicationPermissions) {
				clearUserGroup(applicationPermissions);
				return true;
			}

			@Override
			public boolean clearAll() {
				clear();
				return true;
			}
						
		});
	}
	
	public void clear(User user) {
		this.setRelationship(user, userAppAuthorizedRel, null);
		this.setRelationship(user, userAppUnAuthorizedRel, null);
	}
	
	private void clear(){
		for(User user : UserManager.get().findAll()){
			clear(user);
		}
	}
		
	private void clearUserGroup(UserGroup userGroup) {
		for(User user: userGroup.getUsers()){
			clear(user);
		}
	}
	
	private void clearUserGroup(ApplicationPermissions applicationPermissions) {
		for(User user: applicationPermissions.getUserGroup().getUsers()){
			clear(user);
		}
	}
	
	public boolean isAuthorized(String appName, String moduleName, User user)
	{
		ApplicationInfo applicationName = ApplicationInfoManager.get().getApplicationInfo(appName);
		ModuleInfo module = ModuleInfoManager.get().getModule(applicationName, moduleName);
		if(isUnAuthorized(module, user)) return false;
		ModuleInfo allModule = ModuleInfoManager.get().getModule(applicationName); 
		if(isUnAuthorized(allModule, user)) return false;
		ModuleInfo allAllModule = ModuleInfoManager.ALL;
		if(isUnAuthorized(allAllModule, user)) return false;
		if(isAuthorized(module, user)) return true;
		if(isAuthorized(allModule, user)) return true;
		if(isAuthorized(allAllModule, user)) return true;
		return false;
	}
	
	public boolean isAuthorized(String appName, User user)
	{
		ApplicationInfo applicationInfo = ApplicationInfoManager.get().getApplicationInfo(appName);
		ModuleInfo allModule = ModuleInfoManager.get().getModule(applicationInfo); 
		if(isUnAuthorized(allModule, user)) return false;
		ModuleInfo allAllModule = ModuleInfoManager.ALL;
		if(isUnAuthorized(allAllModule, user)) return false;
		for(ModuleInfo moduleInfo : ModuleInfoManager.get().getModules(applicationInfo)){
			if(isAuthorized(applicationInfo.getAppName(), moduleInfo.getModuleName(), user)) return true;
		}
		return false;
	}
	
	private boolean isAuthorized(ModuleInfo moduleInfo, User user){
		HashSet<?> moduleNames = this.getRelationship(user, userAppAuthorizedRel);
		 if(moduleNames == null){
			 buildUserAppsRelationShip(user);
			 moduleNames = this.getRelationship(user, userAppAuthorizedRel);
		 }
		 return moduleNames.contains(moduleInfo);
	}

	private boolean isUnAuthorized(ModuleInfo moduleInfo, User user){
		HashSet<?> moduleNames = this.getRelationship(user, userAppUnAuthorizedRel);
		 if(moduleNames == null){
			 buildUserAppsRelationShip(user);
			 moduleNames = this.getRelationship(user, userAppUnAuthorizedRel);
		 }
		 return moduleNames.contains(moduleInfo);
	}
		
	private void buildUserAppsRelationShip(User user){
		HashSet<ModuleInfo> authorizedModuleInfos = new HashSet<ModuleInfo>();
		HashSet<ModuleInfo> unAuthorizedModuleInfos = new HashSet<ModuleInfo>();
		Set<ApplicationPermissions> permissions = getPermissions(user);
		for(ApplicationPermissions permission : permissions){
			ModuleInfo module = permission.getModule();
			if(module == ModuleInfoManager.NULL) continue;
			if(permission.isAuthorized()) {
				authorizedModuleInfos.add(module);
			}else{
				unAuthorizedModuleInfos.add(permission.getModule());
			}
		}
		this.setRelationship(user, userAppAuthorizedRel, authorizedModuleInfos);
		this.setRelationship(user, userAppUnAuthorizedRel, unAuthorizedModuleInfos);
	}
	
	private Set<ApplicationPermissions> getPermissions(User user){
		List<UserGroup> userGroups = UserGroupManager.get().getUserGroups(user);
		Set<ApplicationPermissions> permissions = new HashSet<ApplicationPermissions>();
		for(UserGroup group : userGroups){
			permissions.addAll(ApplicationPermissionsManager.get().getPermissions(group));
		}
		return permissions;
	}
	
}
