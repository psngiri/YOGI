package yogi.server.gui.report.app;

import yogi.base.app.BaseModule;
import yogi.base.app.ErrorReporter;
import yogi.base.io.resource.db.DbResource;
import yogi.remote.server.CommandServerImpl;
import yogi.server.gui.app.ApplicationStartUpModule;

public class ReportStartUpModule extends BaseModule {
	public static boolean RUN = true;
	private final DbResource dbResource;
	
	public ReportStartUpModule(DbResource ngpDbResource) {
		super();
		this.dbResource=ngpDbResource;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	@Override
	public void setup() {
		this.addProcessor(new ApplicationStartUpModule(dbResource));
		this.addProcessor(new ReportReaderModule(dbResource));
		this.addProcessor(new ReportWriterLoopModule(dbResource));		
		this.addProcessor(new ReportReaderLoopModule(dbResource));
	}

	@Override
	public void run() {
		ErrorReporter.get().info("Taking Command Server OFFLINE..");
		CommandServerImpl.setServerAlive(false);//server offline
		super.run();
		CommandServerImpl.setServerAlive(true);//server online
	}
	
}