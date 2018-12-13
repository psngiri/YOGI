package yogi.server.gui.user;

import yogi.base.relationship.RelationshipObjectImpl;

/**
 * @author Vikram Vadavala
 *
 */
public class UserImpl extends RelationshipObjectImpl<User> implements User {
	
	private String id;

	protected UserImpl(String id) {
		super();
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	public String getName() {
		return id;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public String getIndex() {
		return id;
	}

	@Override
	public int compareTo(User o) {
		return id.compareTo(o.getId());
	}
	
	

}
