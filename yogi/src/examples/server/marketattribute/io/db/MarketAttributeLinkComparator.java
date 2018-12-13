package examples.server.marketattribute.io.db;

import yogi.base.io.link.LinkComparator;

import examples.server.market.Market;
import examples.server.marketattribute.MarketAttributeCreator;
/**
 * @author Vikram Vadavala
 *
 */


public class MarketAttributeLinkComparator implements LinkComparator<MarketAttributeCreator, Market> {

	/* 
	 * Sorts RecordCreator objects
	 */
	public int compareFrom(MarketAttributeCreator from1, MarketAttributeCreator from2) {
		int result = from1.getMarket().compareTo(from2.getMarket());
		if(result != 0) return result;
		return result;
	}

	/* 
	 * Links both if they are equal
	 */
	public int compareFromTo(MarketAttributeCreator from, Market to) {
		int result = from.getMarket().compareTo(to.getMarket());
		if(result != 0) return result;
		return result;
	}

	/* 
	 * Sorts Key objects
	 */
	public int compareTo(Market to1, Market to2) {
		int result = to1.getMarket().compareTo(to2.getMarket());
		if(result != 0) return result;
		return result;
	}

}
