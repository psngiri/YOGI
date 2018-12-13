package examples.server.fare.farekey;

import yogi.base.Factory;
/**
 * @author Vikram Vadavala
 *
 */
public class FareKeyFactory extends Factory<FareKey> {
	private static FareKeyFactory itsInstance = new FareKeyFactory(FareKeyManager.get());

	protected FareKeyFactory(FareKeyManager manager) {
		super(manager);
	}

	public static FareKeyFactory get() {
		return itsInstance;
	}
}
