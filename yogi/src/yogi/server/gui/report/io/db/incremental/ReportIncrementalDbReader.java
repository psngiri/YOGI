package yogi.server.gui.report.io.db.incremental;

import java.util.Collection;

import yogi.base.app.TimeWindowPauseLoopChecker;
import yogi.base.io.db.DbRecord;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.record.io.db.RecordIncrementalDbReader;
import yogi.server.gui.report.Report;
import yogi.server.gui.report.ReportCreator;
import yogi.server.gui.report.ReportData;
import yogi.server.gui.report.ReportFactory;
import yogi.server.gui.report.ReportManager;
import yogi.server.gui.report.ReportValidator;
import yogi.server.gui.report.io.db.ReportDbScanner;
import yogi.server.gui.report.key.ReportKey;
import yogi.server.gui.report.key.ReportKeyManager;


public class ReportIncrementalDbReader extends RecordIncrementalDbReader<ReportKey, ReportData, Report, ReportCreator> {
	
	public static boolean RUN = true;
	
	public ReportIncrementalDbReader(Collection<DbRecord> dbRecords, TimeWindowPauseLoopChecker loopChecker) {
		super(dbRecords, loopChecker, ReportManager.get(), ReportKeyManager.get(), ReportFactory.get(), new ReportDbScanner(),new ReportValidator());
	}

	public ReportIncrementalDbReader(DbResource resource, TimeWindowPauseLoopChecker loopChecker) {
		super(resource, loopChecker, ReportManager.get(), ReportKeyManager.get(), ReportFactory.get(), new ReportDbScanner(),new ReportValidator());
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	@Override
	protected String getTableType() {
		return "TableType";
	}
	
}