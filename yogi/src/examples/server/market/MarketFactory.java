package examples.server.market;

import yogi.base.Factory;

public class MarketFactory extends Factory<Market> {
	private static MarketFactory itsInstance = new MarketFactory(MarketManager.get());

	protected MarketFactory(MarketManager manager) {
		super(manager);
	}

	public static MarketFactory get() {
		return itsInstance;
	}
}
