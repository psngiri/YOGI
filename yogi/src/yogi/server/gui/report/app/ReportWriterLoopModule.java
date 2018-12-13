package yogi.server.gui.report.app;

import yogi.base.Selector;
import yogi.base.app.db.SingleConnectionDbModule;
import yogi.base.io.resource.db.DbResource;
import yogi.base.io.resource.db.SingleConnectionDbResource;
import yogi.server.gui.record.base.BaseRecord;
import yogi.server.gui.record.base.app.incremental.BaseIncrementalWriterLoopModule;
import yogi.server.gui.record.key.Key;

public class ReportWriterLoopModule extends BaseIncrementalWriterLoopModule {
	
	public static boolean RUN = false;	

	public ReportWriterLoopModule(DbResource dbResource) {
		super(dbResource);
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	@Override
	protected void setup(SingleConnectionDbModule singleConnectionDbModule,	Selector<BaseRecord<?>> selector, Selector<Key> keySelector) {
		SingleConnectionDbResource dbResource = singleConnectionDbModule.getDbResource();
		singleConnectionDbModule.addProcessor(new ReportDumpDbWriterModule(dbResource, keySelector, selector));
		singleConnectionDbModule.addProcessor(new ReportDbWriterModule(dbResource, selector));
	}

}