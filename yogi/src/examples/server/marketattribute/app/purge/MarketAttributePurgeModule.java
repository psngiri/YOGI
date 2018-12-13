package examples.server.marketattribute.app.purge;

import yogi.base.Manager;
import yogi.base.io.resource.db.DbResource;
import yogi.server.base.purge.io.db.PurgeDbORDbFileWriter;
import yogi.server.gui.record.base.BaseRecordManager;
import yogi.server.gui.record.base.app.purge.BaseRecordPurgeModule;

import examples.server.market.Market;
import examples.server.market.MarketManager;
import examples.server.marketattribute.MarketAttribute;
import examples.server.marketattribute.MarketAttributeManager;
import examples.server.marketattribute.io.db.purge.MarketAttributePurgeDbWriter;


public class MarketAttributePurgeModule extends BaseRecordPurgeModule<Market, MarketAttribute> {
	
	public static boolean RUN = true;

	public MarketAttributePurgeModule(DbResource dbResource, long timestamp) {
		super(dbResource, timestamp);
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	@Override
	protected PurgeDbORDbFileWriter<MarketAttribute> getWriter(DbResource dbResource) {
		return new MarketAttributePurgeDbWriter(dbResource);
	}

	@Override
	protected BaseRecordManager<Market, MarketAttribute> getRecordManager() {
		return MarketAttributeManager.get();
	}

	@Override
	protected Manager<Market> getKeyManager() {
		return MarketManager.get();
	}

}
