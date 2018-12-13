package examples.server.marketattribute;

import yogi.server.gui.record.base.BaseRecord;

import examples.server.market.Market;

public interface MarketAttribute extends BaseRecord<Market> {
	Market getMarket();
	String getAttribute();
}
