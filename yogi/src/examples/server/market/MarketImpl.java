package examples.server.market;

import yogi.base.relationship.RelationshipObjectImpl;


public class MarketImpl extends RelationshipObjectImpl<Market> implements Market {
	
	private String marketString;
	
	protected MarketImpl(String marketString) {
		super();
		this.marketString=marketString;
	}

	public String getName() {
		return marketString;
	}
	
	@Override
	public String getIndex() {
		return marketString;
	}

	@Override
	public String getMarket() {
		return marketString;
	}

	@Override
	public String toString() {
		return marketString;
	}

	@Override
	public int compareTo(Market market) {
		if(this == market) return 0;
		return marketString.compareTo(market.getMarket());
	}

}
