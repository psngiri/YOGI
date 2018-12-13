package yogi.server.gui.usergroup;

import java.util.Collection;
import java.util.List;

import yogi.base.indexing.Index;
import yogi.base.relationship.RelationshipObject;
import yogi.server.gui.user.User;

public interface UserGroup extends RelationshipObject, Index<String> {
	String getGroupName();
	List<User> getUsers();
	void addUser(User user);
	void addUsers(Collection<User> users);
	void removeUser(User user);
	void removeUsers(Collection<User> users);	
}
