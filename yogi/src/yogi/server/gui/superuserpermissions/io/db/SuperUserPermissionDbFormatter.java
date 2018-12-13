package yogi.server.gui.superuserpermissions.io.db;

import yogi.base.io.db.DbFormatter;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.ObjectDbRecord;
import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.ClassPathResource;
import yogi.server.gui.superuserpermissions.SuperUserPermission;

public class SuperUserPermissionDbFormatter implements DbFormatter<SuperUserPermission> {
	
	private ObjectDbRecord dbRecord = new ObjectDbRecord(0);

	public String query() {		
		return new QueryReader(new ClassPathResource("insertQuery.txt", this.getClass())).read();
	}

	public String cleanUpQuery() {
		return new QueryReader(new ClassPathResource("cleanUpQuery.txt", this.getClass())).read();
	}

	public DbRecord format(SuperUserPermission superUserPermission) {
		dbRecord.clear();
		return dbRecord;
	}

}
