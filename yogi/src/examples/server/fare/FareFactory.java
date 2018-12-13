package examples.server.fare;

import yogi.base.Factory;

public class FareFactory extends Factory<Fare> {
	private static FareFactory itsInstance = new FareFactory(FareManager.get());

	protected FareFactory(FareManager manager) {
		super(manager);
	}

	public static FareFactory get() {
		return itsInstance;
	}
}
