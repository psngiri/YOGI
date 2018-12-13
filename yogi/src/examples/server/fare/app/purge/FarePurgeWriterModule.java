package examples.server.fare.app.purge;
/**
 * @author Vikram Vadavala
 *
 */
import yogi.base.app.db.SingleConnectionDbModule;
import yogi.base.io.resource.db.DbResource;

import examples.server.fare.io.db.purge.FarePurgeDbWriter;

public class FarePurgeWriterModule extends SingleConnectionDbModule {
	public static boolean RUN = true;
	private DbResource dbResource;

	public FarePurgeWriterModule(DbResource dbResource) {
		super(dbResource);
		this.dbResource = dbResource;
	}

	@Override
	public boolean isActivated() {
		return dbResource==null ? false : RUN;
	}

	public void setup() {
		this.addWriter(new FarePurgeDbWriter(getDbResource()));
	}
	
}
