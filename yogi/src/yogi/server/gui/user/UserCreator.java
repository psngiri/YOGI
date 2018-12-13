package yogi.server.gui.user;

import yogi.base.Creator;

/**
 * @author Vikram Vadavala
 *
 */
public class UserCreator implements Creator<User> {
	private String id;
	
	public User create() {
		return new UserImpl(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id;
	}
	
}
