package yogi.server.gui.user;

import yogi.base.validation.ValidatorAdapter;

public class UserValidator extends ValidatorAdapter<User> {
	@Override
	public boolean validate(User user) {
		return true;
	}
}
