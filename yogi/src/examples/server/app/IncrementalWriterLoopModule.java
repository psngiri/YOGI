package examples.server.app;

import yogi.base.Selector;
import yogi.base.app.db.SingleConnectionDbModule;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.record.base.BaseRecord;
import yogi.server.gui.record.base.app.incremental.BaseIncrementalWriterLoopModule;
import yogi.server.gui.record.key.Key;

import examples.server.marketattribute.io.db.incremental.MarketAttributeIncrementalDbWriter;

public class IncrementalWriterLoopModule extends BaseIncrementalWriterLoopModule {
	public static boolean RUN = false;	
	
	public IncrementalWriterLoopModule(DbResource dbResource) {
		super(dbResource);
	}	
	
	@Override
	public boolean isActivated() {
		return RUN;
	}

	protected void setup(SingleConnectionDbModule singleConnectionDbModule, Selector<BaseRecord<?>> selector, Selector<Key> keySelector){
		singleConnectionDbModule.addWriter(new MarketAttributeIncrementalDbWriter(singleConnectionDbModule.getDbResource(), selector));
		
	}
	
}