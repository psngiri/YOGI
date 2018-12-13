package yogi.server.gui.report.key.io.db;

import yogi.base.Selector;
import yogi.base.io.db.DbRecord;


public class ReportKeyDbRecordSelector implements Selector<DbRecord> {

	public boolean select(DbRecord dbRecord) {
		return true;
	}

}
