package examples.server.sub;

import yogi.base.validation.ValidatorAdapter;

public class SubValidator extends ValidatorAdapter<Sub> {
	@Override
	public boolean validate(Sub sub) {
		return true;
	}
}
