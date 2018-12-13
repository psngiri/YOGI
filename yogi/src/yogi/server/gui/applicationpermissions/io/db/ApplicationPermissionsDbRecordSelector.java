package yogi.server.gui.applicationpermissions.io.db;

import yogi.base.Selector;
import yogi.base.io.db.DbRecord;


public class ApplicationPermissionsDbRecordSelector implements Selector<DbRecord> {

	public boolean select(DbRecord dbRecord) {
		return true;
	}

}
