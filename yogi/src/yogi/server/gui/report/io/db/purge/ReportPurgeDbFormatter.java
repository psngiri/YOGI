package yogi.server.gui.report.io.db.purge;

import yogi.server.gui.record.purge.db.RecordPurgeDbFormatter;
import yogi.server.gui.report.Report;

/**
 * @author Vikram Vadavala
 *
 * @param <T>
 */
public class ReportPurgeDbFormatter extends RecordPurgeDbFormatter<Report> {

	public ReportPurgeDbFormatter() {
		super("Query Tool");
	}
	
	protected String getTableName() {
		return "REPORT_QUERIES";
	}

}
