package examples.server.fare.app;

import yogi.base.app.BaseModule;
import yogi.base.io.resource.db.DbResource;

import examples.server.fare.farekey.io.db.FareKeyDbReader;
import examples.server.fare.io.db.FareDbReader;

public class FareModule extends BaseModule {
	public static boolean RUN = true;
	private DbResource dbResource;
	
	public FareModule(DbResource dbResource) {
		super();
		this.dbResource=dbResource;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		addReader(new FareKeyDbReader(dbResource));
		addReader(new FareDbReader(dbResource));
	}

}
