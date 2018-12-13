package yogi.auth;

import yogi.remote.client.app.CommandAdapter;


public abstract class AuthUserCommandAdapter<R> extends CommandAdapter<R> {

	private static final long serialVersionUID = -2636169438858918989L;
	private AuthUser authUser;
	
	public AuthUserCommandAdapter(String userId) {
		super(userId);
	}

	public AuthUserCommandAdapter(AuthUser authUser) {
		super(authUser.getUserId());
		this.authUser = authUser;
	}

	@Override
	public abstract R execute();

	public AuthUser getAuthUser() {
		return authUser;
	}
		
}