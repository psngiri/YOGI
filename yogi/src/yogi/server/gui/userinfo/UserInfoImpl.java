package yogi.server.gui.userinfo;

import yogi.base.relationship.RelationshipObjectImpl;
import yogi.server.gui.user.User;

/**
 * @author Vikram Vadavala
 *
 */
public class UserInfoImpl extends RelationshipObjectImpl<UserInfo> implements UserInfo {
	
	private User user;
	private String firstName;
	private String lastName;
	
	protected UserInfoImpl(User user,String firstName,String lastName) {
		super();
		this.user=user;
		this.firstName=firstName;
		this.lastName=lastName;
	}


	public User getUser() {
		return user;
	}


	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getName() {
		return user.getName();
	}

	@Override
	public String toString() {
		return user.toString()+"/"+firstName+"/"+lastName;
	}

	@Override
	public int compareTo(UserInfo userinfo) {
		return user.compareTo(userinfo.getUser());
	}


	@Override
	public String getUserName() {
		return firstName+" "+lastName;
	}
	
	

}
