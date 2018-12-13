package yogi.server.gui.report.io.db;

import yogi.server.gui.record.io.db.RecordDbScanner;
import yogi.server.gui.report.Report;
import yogi.server.gui.report.ReportCreator;
import yogi.server.gui.report.ReportData;
import yogi.server.gui.report.key.ReportKey;

public class ReportDbScanner extends RecordDbScanner<ReportKey,ReportData,Report, ReportCreator> {

	@Override
	protected ReportCreator getCreator() {
		return new ReportCreator();
	}

	@Override
	protected Class<ReportData> getRecordDataClass() {
		return ReportData.class;
	}

}
