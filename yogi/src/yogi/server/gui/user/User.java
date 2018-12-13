package yogi.server.gui.user;

import yogi.base.indexing.Index;
import yogi.base.relationship.RelationshipObject;

/**
 * @author Vikram Vadavala
 *
 */
public interface User extends RelationshipObject, Index<String>,Comparable<User>{
	String getId();
}
