package yogi.server.gui.report.app;

import yogi.base.Selector;
import yogi.base.app.BaseModule;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.record.base.BaseRecord;
import yogi.server.gui.report.io.db.ReportDbWriter;
import yogi.server.gui.user.preferences.io.db.UserPreferencesDbWriter;

public class ReportDbWriterModule extends BaseModule {
	public static boolean RUN = true;
	private DbResource dbResource;
	private Selector<BaseRecord<?>> selector;
	
	public ReportDbWriterModule(DbResource dbResource, Selector<BaseRecord<?>> selector) {
		super();
		this.dbResource=dbResource;
		this.selector = selector;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		this.addWriter(new ReportDbWriter(dbResource, selector));
		this.addWriter(new UserPreferencesDbWriter(dbResource, selector));
	}

}
