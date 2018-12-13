package yogi.versioning.version.io.db;

import yogi.base.Selector;
import yogi.base.io.db.DbRecord;


public class VersionRecordSelector implements Selector<DbRecord> {

	public boolean select(DbRecord dbRecord) {
		return true;
	}

}
