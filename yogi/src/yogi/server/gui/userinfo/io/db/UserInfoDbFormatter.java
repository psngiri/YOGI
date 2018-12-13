package yogi.server.gui.userinfo.io.db;

import yogi.base.io.db.DbFormatter;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.ObjectDbRecord;
import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.ClassPathResource;
import yogi.server.gui.userinfo.UserInfo;

public class UserInfoDbFormatter implements DbFormatter<UserInfo> {
	private ObjectDbRecord dbRecord = new ObjectDbRecord(3);

	public String query() {		
		return new QueryReader(new ClassPathResource("insertQuery.txt", this.getClass())).read();
	}

	public String cleanUpQuery() {
		return new QueryReader(new ClassPathResource("cleanUpQuery.txt", this.getClass())).read();
	}

	public DbRecord format(UserInfo userInfo) {
		dbRecord.clear();
		dbRecord.setObject(0, userInfo.getUser().getId());
		dbRecord.setObject(1, userInfo.getFirstName());
		dbRecord.setObject(2, userInfo.getLastName());		
		return dbRecord;
	}
	
}