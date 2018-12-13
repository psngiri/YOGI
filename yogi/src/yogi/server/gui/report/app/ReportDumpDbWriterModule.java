package yogi.server.gui.report.app;

import yogi.base.Selector;
import yogi.base.app.BaseModule;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.report.Report;
import yogi.server.gui.report.io.db.dump.ReportDumpDbWriter;
import yogi.server.gui.report.key.ReportKey;
import yogi.server.gui.report.key.io.db.dump.ReportKeyDumpDbWriter;

public class ReportDumpDbWriterModule extends BaseModule {
	public static boolean RUN = false;
	private final DbResource dbResource;
	private Selector<? super ReportKey> keySelector;
	private Selector<? super Report> selector;
	
	public ReportDumpDbWriterModule(DbResource dbResource,
			Selector<? super ReportKey> keySelector,
			Selector<? super Report> selector) {
		super();
		this.dbResource = dbResource;
		this.keySelector = keySelector;
		this.selector = selector;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	@Override
	public void setup() {
		this.addWriter(new ReportKeyDumpDbWriter(dbResource, keySelector));
		this.addWriter(new ReportDumpDbWriter(dbResource, selector));
	}

}