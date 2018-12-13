package examples.server.marketattribute;

import yogi.server.gui.record.base.BaseRecordFactory;

import examples.server.market.Market;

public class MarketAttributeFactory extends BaseRecordFactory<Market, MarketAttribute> {
	private static MarketAttributeFactory itsInstance = new MarketAttributeFactory(MarketAttributeManager.get());

	protected MarketAttributeFactory(MarketAttributeManager manager) {
		super(manager, true);
	}

	public static MarketAttributeFactory get() {
		return itsInstance;
	}

	@Override
	protected void add(MarketAttribute object) {
		super.add(object);
		System.out.println("added Object "+new MarketAttributeRecord(object));
	}
}
