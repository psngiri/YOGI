package yogi.server.gui.superuserpermissions;

import java.util.List;

import yogi.base.relationship.RelationshipManager;
import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.server.gui.user.User;

public class SuperUserPermissionManager extends RelationshipManager<SuperUserPermission> {
	
	private static SuperUserPermissionManager itsInstance = new SuperUserPermissionManager();
	
	private static OneToManyInverseRelationship<User,SuperUserPermission> superUserPermissionsRel = RelationshipTypeFactory.get().createOneToManyInverseRelationship(User.class, SuperUserPermission.class, "SuperUsers");

	protected SuperUserPermissionManager() {
		super();
	}

	public static SuperUserPermissionManager get() {
		return itsInstance;
	}
	
	@Override
	protected void buildRelationships(SuperUserPermission superUserPermission) {
		this.buildRelationship(superUserPermission.getSuperUser(), superUserPermission, superUserPermissionsRel);
	}

	@Override
	protected void deleteRelationships(SuperUserPermission superUserPermission) {
		this.deleteRelationship(superUserPermission.getSuperUser(), superUserPermission, superUserPermissionsRel);
	}
	
	public List<SuperUserPermission> getSuperUserPermissions(User user){
		return this.getRelationship(user, superUserPermissionsRel);
	}
	
}
