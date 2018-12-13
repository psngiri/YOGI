package examples.server.marketattribute.io.db.incremental;

import yogi.base.Selector;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.record.base.io.db.BaseRecordDbWriter;

import examples.server.market.Market;
import examples.server.marketattribute.MarketAttribute;
import examples.server.marketattribute.MarketAttributeManager;
import examples.server.marketattribute.io.db.MarketAttributeDbReader;

public class MarketAttributeIncrementalDbWriter extends BaseRecordDbWriter<Market, MarketAttribute> {	
	
	public MarketAttributeIncrementalDbWriter(DbResource resource, Selector<? super MarketAttribute> selector){		
		super(resource, selector, new MarketAttributeDbFormatter(), MarketAttributeManager.get(), MarketAttributeDbReader.class);
	}

	public MarketAttributeIncrementalDbWriter(DbResource resource) {
		this(resource, null);
	}
}