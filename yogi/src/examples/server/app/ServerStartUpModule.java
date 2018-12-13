package examples.server.app;

import yogi.base.app.BaseModule;
import yogi.base.app.ErrorReporter;
import yogi.base.io.resource.db.DbResource;
import yogi.remote.server.CommandServerImpl;

import examples.server.fare.app.FareModule;
import examples.server.marketattribute.io.db.MarketAttributeDbReader;
import examples.server.sub.io.db.SubDbReader;

/**
 * @author Vikram Vadavala
 *
 */
public class ServerStartUpModule extends BaseModule {
	public static boolean RUN = true;
	private final DbResource dbResource;
	
	public ServerStartUpModule(DbResource dbResource) {
		super();
		this.dbResource=dbResource;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	@Override
	public void setup() {
		this.addReader(new SubDbReader(dbResource));
		this.addProcessor(new FareModule(dbResource));
		this.addReader(new MarketAttributeDbReader(dbResource));
		this.addProcessor(new IncrementalReaderLoopModule(dbResource));
		this.addProcessor(new IncrementalWriterLoopModule(dbResource));
	}

	@Override
	public void run() {
		CommandServerImpl.setServerAlive(false);//server offline
		ErrorReporter.get().info("Server OFFLINE..");
		super.run();
		CommandServerImpl.setServerAlive(true);//server online
		ErrorReporter.get().info("Server ONLINE..");
	}

}