package examples.server.market;

import yogi.base.Creator;

public class MarketCreator implements Creator<Market> {
	
	private String market;
	
	public Market create() {
		return new MarketImpl(market);
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	@Override
	public String toString() {
		return market;
	}
	
}
