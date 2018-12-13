package yogi.server.gui.report.key.io.db;

import yogi.server.gui.record.key.io.db.KeyDbScanner;
import yogi.server.gui.report.key.ReportKey;
import yogi.server.gui.report.key.ReportKeyCreator;

public class ReportKeyDbScanner extends KeyDbScanner<ReportKey, ReportKeyCreator> {

	@Override
	protected ReportKeyCreator getCreator() {
		return new ReportKeyCreator();
	}
}
