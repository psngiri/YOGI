package yogi.server.gui.usergroup;

import java.util.List;

import yogi.base.ObjectNotFoundException;
import yogi.base.relationship.index.IndexedManager;
import yogi.base.relationship.types.OneToManyInverseToADirectRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.server.gui.user.User;

public class UserGroupManager extends IndexedManager<UserGroup, String> {
	private static UserGroupManager itsInstance = new UserGroupManager();
	private static UserGroupCreator creator = new UserGroupCreator();
	static OneToManyInverseToADirectRelationship<User, UserGroup> userRel = RelationshipTypeFactory.get().createOneToManyInverseToADirectRelationship(User.class, UserGroup.class, "UserGroups");
	public static UserGroup NULL = new UserGroupImpl(null);

	protected UserGroupManager() {
		super();
	}

	public static UserGroupManager get() {
		return itsInstance;
	}
	
	public UserGroup getUserGroup(String groupName){
		return getUserGroup(groupName, false);
	}

	public UserGroup getUserGroup(String groupName, boolean createIfNeeded){
		if(groupName == null || groupName.trim().isEmpty()) return NULL;
		groupName = groupName.trim();
		try {
			return this.getObject(groupName);
		} catch (ObjectNotFoundException e) {
			if(createIfNeeded) return createUserGroup(groupName);
		}
		return NULL;
	}
	
	private synchronized UserGroup createUserGroup(String groupName) {
		try {
			return this.getObject(groupName);
		} catch (ObjectNotFoundException e) {
			creator.setGroupName(groupName);
			return UserGroupFactory.get().create(creator);
		}
	}

	public List<UserGroup> getUserGroups(User user){
		return this.getRelationship(user, userRel);
	}
	
}
