package yogi.server.gui.report.app;

import yogi.base.app.TimeWindowPauseLoopChecker;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.record.base.app.incremental.BaseIncrementalReaderLoopModule;
import yogi.server.gui.report.io.db.incremental.ReportIncrementalDbReader;

public class ReportReaderLoopModule extends BaseIncrementalReaderLoopModule {
	public static boolean RUN = false;	
	public ReportReaderLoopModule(DbResource dbResource) {
		super(dbResource);
	}

	@Override
	protected void setup(DbResource dbResource,	TimeWindowPauseLoopChecker loopChecker) {
		this.addReader(new ReportIncrementalDbReader(dbResource, loopChecker));
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

}