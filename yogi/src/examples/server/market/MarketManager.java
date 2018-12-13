package examples.server.market;

import yogi.base.ObjectNotFoundException;
import yogi.base.app.ErrorReporter;
import yogi.base.relationship.index.IndexedManager;

public class MarketManager extends IndexedManager<Market, String> {
	private static MarketManager itsInstance = new MarketManager();
	private static MarketCreator marketCreator = new MarketCreator();
	public static Market ANY = new MarketImpl("any");

	protected MarketManager() {
		super();
	}

	public static MarketManager get() {
		return itsInstance;
	}

	public Market getMarket(String market)
	{
		if (null == market) {
			ErrorReporter.get().info("Could Not Find Market ", market);
			return ANY;
		}
		market = market.trim();
		try {
			return this.getObject(market);
		} catch (ObjectNotFoundException e) {
			return createMarket(market);
		}
	}

	private synchronized Market createMarket(String market)
	{
		try {
			return this.getObject(market);
		} catch (ObjectNotFoundException e) {
			ErrorReporter.get().info("Creating Market ", market);
			marketCreator.setMarket(market);
			return MarketFactory.get().create(marketCreator);
		}
	}
	
}
