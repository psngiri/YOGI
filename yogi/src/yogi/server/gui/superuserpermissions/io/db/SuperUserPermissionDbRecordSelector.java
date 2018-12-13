package yogi.server.gui.superuserpermissions.io.db;

import yogi.base.Selector;
import yogi.base.io.db.DbRecord;


public class SuperUserPermissionDbRecordSelector implements Selector<DbRecord> {

	public boolean select(DbRecord dbRecord) {
		return true;
	}

}
