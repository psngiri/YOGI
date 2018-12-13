package examples.server.marketattribute;

import yogi.server.gui.record.base.BaseRecordCreator;

import examples.server.market.Market;
import examples.server.market.MarketManager;

public class MarketAttributeCreator extends BaseRecordCreator<Market, MarketAttribute> {
	
	private String market;
	private String attribute;
	
	@Override
	public MarketAttribute create() {
		Market key = getKey();		
		return new MarketAttributeImpl(key, getAction(), getTimeStamp(), getModifiedByUser(), attribute);
	}

	@Override
	public Market getKey() {
		return MarketManager.get().getMarket(market);
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

}
