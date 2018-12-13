package examples.server.marketattribute.io.db;
/**
 * @author Vikram Vadavala
 *
 */
import java.util.List;

import yogi.base.io.link.LinkGeneratorImpl;

import examples.server.market.Market;
import examples.server.market.MarketManager;
import examples.server.marketattribute.MarketAttributeCreator;

public class MarketAttributeLinkGenerator extends LinkGeneratorImpl<MarketAttributeCreator, Market>{

	public MarketAttributeLinkGenerator() {
		super(new MarketAttributeLinkComparator());
	}

	@Override
	protected void buildLink(MarketAttributeCreator from, Market to) {
		from.setKey(to);
	}

	@Override
	public List<Market> getToObjects() {
		return MarketManager.get().findAll();
	}

 

}
