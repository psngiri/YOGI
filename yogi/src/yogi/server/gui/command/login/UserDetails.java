package yogi.server.gui.command.login;

import java.io.Serializable;
import java.util.LinkedHashMap;

import yogi.auth.AuthUser;
import yogi.server.gui.userinfo.UserInfo;

public class UserDetails implements Serializable{
	

	private static final long serialVersionUID = -3398248412946548838L;

	private String userId;
	private LinkedHashMap<String,UserInfo> userInfos;
	private AuthUser authUser;
	private String message;
	
	public UserDetails(String userId, LinkedHashMap<String,UserInfo> userInfo, AuthUser authUser) {
		super();
		this.userId = userId;
		this.userInfos = userInfo;
		this.authUser = authUser;

		if(userInfo==null){
			message = "User "+ userId +" is not authorized. Please contact system administrator.";
		}
	}
	
	public String getUserId() {
		return userId;
	}

	public LinkedHashMap<String,UserInfo> getUserInfo() {
		return userInfos;
	}

	public AuthUser getAuthUser() {
		return authUser;
	}

	public String getMessage() {
		return message;
	}
}
