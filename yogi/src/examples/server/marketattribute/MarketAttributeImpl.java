package examples.server.marketattribute;

import yogi.server.action.Action;
import yogi.server.gui.record.base.BaseRecordImpl;
import yogi.server.gui.user.User;

import examples.server.market.Market;


public class MarketAttributeImpl extends BaseRecordImpl<Market, MarketAttribute> implements MarketAttribute {
	
	private String attribute;
	
	public MarketAttributeImpl(Market market, Action action, long timestamp, User modifiedByUser, 
			String attribute) {
		super(market, action, timestamp, modifiedByUser);
		this.attribute = attribute;
	}

	public Market getMarket() {
		return getKey();
	}

	@Override
	public String getAttribute() {
		return attribute;
	}

}