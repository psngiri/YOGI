package examples.server.fare.farekey;

import yogi.base.validation.ValidatorAdapter;
/**
 * @author Vikram Vadavala
 *
 */
public class FareKeyValidator extends ValidatorAdapter<FareKey> {
	@Override
	public boolean validate(FareKey fareKey) {
		return true;
	}
}
