package yogi.server.gui.applicationpermissions;

import java.util.List;

import yogi.base.relationship.RelationshipManager;
import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.server.gui.applicationinfo.ApplicationInfo;
import yogi.server.gui.moduleinfo.ModuleInfo;
import yogi.server.gui.usergroup.UserGroup;

public class ApplicationPermissionsManager extends RelationshipManager<ApplicationPermissions> {
	private static ApplicationPermissionsManager itsInstance = new ApplicationPermissionsManager();
	private static OneToManyInverseRelationship<ApplicationInfo,ApplicationPermissions> appInfoPermissionsRel = RelationshipTypeFactory.get().createOneToManyInverseRelationship(ApplicationInfo.class, ApplicationPermissions.class, "ApplicationInfos");  
	private static OneToManyInverseRelationship<ModuleInfo,ApplicationPermissions> moduleInfoPermissionsRel = RelationshipTypeFactory.get().createOneToManyInverseRelationship(ModuleInfo.class, ApplicationPermissions.class, "ModuleInfos");  
	private static OneToManyInverseRelationship<UserGroup,ApplicationPermissions> userInfoPermissionsRel = RelationshipTypeFactory.get().createOneToManyInverseRelationship(UserGroup.class, ApplicationPermissions.class, "UserGroups");  

	protected ApplicationPermissionsManager() {
		super();
	}

	public static ApplicationPermissionsManager get() {
		return itsInstance;
	}
	
	@Override
	protected void buildRelationships(ApplicationPermissions applicationPermissions) {
		this.buildRelationship(applicationPermissions.getApplicationInfo(), applicationPermissions, appInfoPermissionsRel);
		this.buildRelationship(applicationPermissions.getModule(), applicationPermissions, moduleInfoPermissionsRel);
		this.buildRelationship(applicationPermissions.getUserGroup(), applicationPermissions, userInfoPermissionsRel);
	}

	@Override
	protected void deleteRelationships(ApplicationPermissions applicationPermissions) {
		this.deleteRelationship(applicationPermissions.getApplicationInfo(), applicationPermissions, appInfoPermissionsRel);
		this.deleteRelationship(applicationPermissions.getModule(), applicationPermissions, moduleInfoPermissionsRel);
		this.deleteRelationship(applicationPermissions.getUserGroup(), applicationPermissions, userInfoPermissionsRel);
	}
	
	public List<ApplicationPermissions> getPermissions(ApplicationInfo applicationInfo){
		return this.getRelationship(applicationInfo, appInfoPermissionsRel);
	}
	
	public List<ApplicationPermissions> getPermissions(ModuleInfo moduleInfo){
		return this.getRelationship(moduleInfo, moduleInfoPermissionsRel);
	}
	
	public List<ApplicationPermissions> getPermissions(UserGroup userGroup){
		return this.getRelationship(userGroup, userInfoPermissionsRel);
	}
	
}
