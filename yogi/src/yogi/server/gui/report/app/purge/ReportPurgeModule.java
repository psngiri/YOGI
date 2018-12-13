package yogi.server.gui.report.app.purge;
/**
 * @author Vikram Vadavala
 *
 */
import yogi.base.io.resource.db.DbResource;
import yogi.server.base.purge.io.db.PurgeDbORDbFileWriter;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.record.app.purge.RecordPurgeModule;
import yogi.server.gui.record.key.KeyManager;
import yogi.server.gui.report.Report;
import yogi.server.gui.report.ReportData;
import yogi.server.gui.report.ReportManager;
import yogi.server.gui.report.io.db.purge.ReportPurgeDbWriter;
import yogi.server.gui.report.key.ReportKey;
import yogi.server.gui.report.key.ReportKeyManager;


public class ReportPurgeModule extends RecordPurgeModule<ReportKey, ReportData, Report> {
	
	public static boolean RUN = true;
	
	public ReportPurgeModule(DbResource dbResource, long timestamp) {
		super(dbResource, timestamp);
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	@Override
	protected PurgeDbORDbFileWriter<Report> getWriter(DbResource dbResource) {
		return new ReportPurgeDbWriter(dbResource);
	}

	@Override
	protected RecordManager<ReportKey, ReportData, Report> getRecordManager() {
		return ReportManager.get();
	}

	@Override
	protected KeyManager<ReportKey> getKeyManager() {
		return ReportKeyManager.get();
	}

}
