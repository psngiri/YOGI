package examples.server.app;

import yogi.base.app.TimeWindowPauseLoopChecker;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.record.base.app.incremental.BaseIncrementalReaderLoopModule;

import examples.server.marketattribute.io.db.incremental.MarketAttributeIncrementalDbReader;

public class IncrementalReaderLoopModule extends BaseIncrementalReaderLoopModule {
	public static boolean RUN = false;	
	public IncrementalReaderLoopModule(DbResource dbResource) {
		super(dbResource);
	}

	@Override
	protected void setup(DbResource dbResource,	TimeWindowPauseLoopChecker loopChecker) {
		this.addReader(new MarketAttributeIncrementalDbReader(dbResource, loopChecker));
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

}