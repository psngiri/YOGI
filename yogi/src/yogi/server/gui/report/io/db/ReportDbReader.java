package yogi.server.gui.report.io.db;

import java.util.Collection;

import yogi.base.io.db.DbRecord;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.record.io.db.RecordDbReader;
import yogi.server.gui.report.Report;
import yogi.server.gui.report.ReportCreator;
import yogi.server.gui.report.ReportData;
import yogi.server.gui.report.ReportFactory;
import yogi.server.gui.report.ReportValidator;
import yogi.server.gui.report.key.ReportKey;
import yogi.server.gui.report.key.ReportKeyManager;

public class ReportDbReader extends RecordDbReader<ReportKey,ReportData,Report, ReportCreator> {
	public static boolean RUN = true;
	
	public ReportDbReader(DbResource resource) {
		super(resource, ReportKeyManager.get(), ReportFactory.get(), new ReportDbScanner(), new ReportValidator());
	}

	public ReportDbReader(Collection<DbRecord> scopes) {
		super(scopes, ReportKeyManager.get(), ReportFactory.get(), new ReportDbScanner(), new ReportValidator());
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}
}
