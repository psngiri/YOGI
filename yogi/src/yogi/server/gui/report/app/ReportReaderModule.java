package yogi.server.gui.report.app;

import yogi.base.app.BaseModule;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.report.io.db.ReportDbReader;
import yogi.server.gui.report.key.io.db.ReportKeyDbReader;

public class ReportReaderModule extends BaseModule {
	public static boolean RUN = true;
	private DbResource ngpDbResource;
	
	public ReportReaderModule(DbResource ngpDbResource) {
		super();
		this.ngpDbResource=ngpDbResource;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		addReader(new ReportKeyDbReader(ngpDbResource));
		addReader(new ReportDbReader(ngpDbResource));
	}

}
