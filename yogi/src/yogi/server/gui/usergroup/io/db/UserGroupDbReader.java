package yogi.server.gui.usergroup.io.db;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.usergroup.UserGroup;
import yogi.server.gui.usergroup.UserGroupFactory;
import yogi.server.gui.usergroup.UserGroupValidator;


public class UserGroupDbReader extends DefaultDbRecordReader<UserGroup> {
	public static boolean RUN = true;
	
	public UserGroupDbReader(DbResource resource) {
		super(resource);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new DefaultRecordProcessor<UserGroup, DbRecord>(new UserGroupValidator(), new UserGroupDbScanner(), UserGroupFactory.get(), new UserGroupDbRecordSelector()));
	}

	public UserGroupDbReader(Collection<DbRecord> userGroups) {
		super(userGroups);
		setup();
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
}
