package examples.server.fare.app.purge;
/**
 * @author Vikram Vadavala
 *
 */
import yogi.base.app.BaseModule;
import yogi.base.io.resource.db.DbResource;

import examples.server.sub.Sub;


public class FarePurgeModule extends BaseModule {
	public static boolean RUN = true;
	private DbResource dbResource;
	private Sub sub;

	public FarePurgeModule(DbResource dbResource, Sub sub) {
		super();
		this.dbResource=dbResource;
		this.sub = sub;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		this.addProcessor(new FareMarkPurgeProcessor(sub));//marks all the Fares that can be purged
		this.addProcessor(new FarePurgeWriterModule(dbResource));//deletes the above marked Fares from database
		this.addProcessor(new FarePurgeProcessor());//deletes the above marked Fares from memory
	}
}
