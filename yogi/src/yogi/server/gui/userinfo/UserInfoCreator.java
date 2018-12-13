package yogi.server.gui.userinfo;

import yogi.base.Creator;
import yogi.server.gui.user.User;

/**
 * @author Vikram Vadavala
 *
 */
public class UserInfoCreator implements Creator<UserInfo> {
	
	private User user;
	private String firstName;
	private String lastName;
	
	public UserInfo create() {
		return new UserInfoImpl(user, firstName, lastName);
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return user.toString()+"/"+firstName+"/"+lastName;
	}

}
