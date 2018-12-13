package yogi.server.gui.report.key.io.db;

import java.util.Collection;

import yogi.base.io.FinderRecordProcessor;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.report.key.ReportKey;
import yogi.server.gui.report.key.ReportKeyCreator;
import yogi.server.gui.report.key.ReportKeyFactory;
import yogi.server.gui.report.key.ReportKeyValidator;
import yogi.server.gui.report.key.io.ReportKeyFinder;

public class ReportKeyDbReader extends DefaultDbRecordReader<ReportKey> {
	public static boolean RUN = true;
	
	public ReportKeyDbReader(DbResource resource) {
		super(resource);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new FinderRecordProcessor<ReportKey, ReportKeyCreator, DbRecord>(new ReportKeyValidator(), new ReportKeyDbScanner(), ReportKeyFactory.get(), new ReportKeyDbRecordSelector(), new ReportKeyFinder()));
	}

	public ReportKeyDbReader(Collection<DbRecord> scopeKeys) {
		super(scopeKeys);
		setup();
	}
		
	@Override
	public boolean isActivated() {
		return RUN;
	}
}
