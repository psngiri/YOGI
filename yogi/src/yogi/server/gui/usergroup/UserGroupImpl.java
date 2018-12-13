package yogi.server.gui.usergroup;

import java.util.Collection;
import java.util.List;

import yogi.base.relationship.RelationshipObjectImpl;
import yogi.base.relationship.types.OneToManyDirectWithInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.server.gui.applicationpermissions.ApplicationPermissionsAssistant;
import yogi.server.gui.user.User;

public class UserGroupImpl extends RelationshipObjectImpl<UserGroup> implements UserGroup {
	
	private String groupName;
	private static OneToManyDirectWithInverseRelationship<UserGroup, User> userrel = RelationshipTypeFactory.get().createOneToManyDirectWithInverseRelationship(UserGroup.class, User.class, "Users", UserGroupManager.userRel);
	
	protected UserGroupImpl(String groupName) {
		super();
		this.groupName = groupName;
	}
	
	@Override
	public String getGroupName() {
		return groupName;
	}

	@Override
	public List<User> getUsers() {
		return this.getRelationship(userrel);
	}
	
	public void addUser(User user){
		if(!getUsers().contains(user)) {
			this.addToRelationship(userrel, user);
			ApplicationPermissionsAssistant.get().clear(user);
		}
	}
	
	public void addUsers(Collection<User> users){
		for(User user : users){
			addUser(user);
		}
	}
	
	public void removeUser(User user)
	{
		if(getUsers().contains(user)) {
			this.removeFromRelationship(userrel, user);
			ApplicationPermissionsAssistant.get().clear(user);
		}
	}
	
	public void removeUsers(Collection<User> users)
	{
		for(User user : users){
			removeUser(user);
		}
	}
	
	@Override
	public String getIndex() {
		return groupName;
	}
		
	public String getName() {
		return groupName+"/"+getUsers();
	}

	@Override
	public String toString() {
		return groupName+"/"+getUsers();
	}
	
}
