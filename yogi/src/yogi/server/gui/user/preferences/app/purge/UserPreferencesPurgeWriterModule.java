package yogi.server.gui.user.preferences.app.purge;
/**
 * @author Vikram Vadavala
 *
 */
import yogi.base.app.db.SingleConnectionDbModule;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.user.preferences.io.db.purge.UserPreferencesPurgeDbWriter;

public class UserPreferencesPurgeWriterModule extends SingleConnectionDbModule {
	public static boolean RUN = true;
	private DbResource dbResource;

	public UserPreferencesPurgeWriterModule(DbResource dbResource) {
		super(dbResource);
		this.dbResource = dbResource;
	}

	@Override
	public boolean isActivated() {
		return dbResource==null ? false : RUN;
	}

	public void setup() {
		this.addWriter(new UserPreferencesPurgeDbWriter(getDbResource()));
	}
	
}
