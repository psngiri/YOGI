package yogi.server.gui.report.app.purge;
/**
 * @author Vikram Vadavala
 *
 */
import yogi.base.app.db.SingleConnectionDbModule;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.report.io.db.purge.ReportPurgeDbWriter;

public class ReportPurgeWriterModule extends SingleConnectionDbModule {
	public static boolean RUN = true;
	private DbResource dbResource;

	public ReportPurgeWriterModule(DbResource dbResource) {
		super(dbResource);
		this.dbResource = dbResource;
	}

	@Override
	public boolean isActivated() {
		return dbResource==null ? false : RUN;
	}

	public void setup() {
		this.addWriter(new ReportPurgeDbWriter(getDbResource()));
	}
	
}
