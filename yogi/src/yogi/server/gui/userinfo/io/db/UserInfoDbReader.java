package yogi.server.gui.userinfo.io.db;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.userinfo.UserInfo;
import yogi.server.gui.userinfo.UserInfoFactory;
import yogi.server.gui.userinfo.UserInfoValidator;

public class UserInfoDbReader extends DefaultDbRecordReader<UserInfo> {
	public static boolean RUN = true;
	public UserInfoDbReader(DbResource resource) {
		super(resource);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new DefaultRecordProcessor<UserInfo, DbRecord>(new UserInfoValidator(), new UserInfoDbScanner(), UserInfoFactory.get(), new UserInfoDbRecordSelector()));
	}

	public UserInfoDbReader(Collection<DbRecord> userInfos) {
		super(userInfos);
		setup();
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
}
