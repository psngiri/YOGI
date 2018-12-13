package examples.server.fare.farekey;

import yogi.server.base.purge.PurgeableManager;
/**
 * @author Vikram Vadavala
 *
 */
public class FareKeyManager extends PurgeableManager<FareKey> {
	private static FareKeyManager itsInstance = new FareKeyManager();

	protected FareKeyManager() {
		super();
	}

	public static FareKeyManager get() {
		return itsInstance;
	}

	public void purge() {
		super.purge(FareKeyFactory.get());
	}

}
