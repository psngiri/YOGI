package yogi.server.gui.applicationinfo.io.db;

import yogi.base.app.ErrorReporter;
import yogi.base.io.Scanner;
import yogi.base.io.db.DbRecord;
import yogi.server.gui.applicationinfo.ApplicationInfo;
import yogi.server.gui.applicationinfo.ApplicationInfoCreator;
import yogi.server.gui.partition.PartitionManager;

public class ApplicationInfoDbScanner implements Scanner<ApplicationInfo, DbRecord> {
	private ApplicationInfoCreator creator = new ApplicationInfoCreator();

	public ApplicationInfoCreator scan(DbRecord dbRecord) {
		try {
			creator.setAppName(dbRecord.getString(1).trim());
			creator.setPartition(PartitionManager.get().getPartition(dbRecord.getString(2)));
		} catch (Exception e) {
			ErrorReporter.get().error(dbRecord, e);
		}
		return creator;
	}
}
