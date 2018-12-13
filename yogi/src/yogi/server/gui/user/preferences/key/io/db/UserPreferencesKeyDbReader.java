package yogi.server.gui.user.preferences.key.io.db;

import java.util.Collection;

import yogi.base.io.FinderRecordProcessor;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.user.preferences.io.db.UserPreferencesDbReader;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyCreator;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyFactory;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyValidator;
import yogi.server.gui.user.preferences.key.io.UserPreferencesKeyFinder;

public class UserPreferencesKeyDbReader extends DefaultDbRecordReader<UserPreferencesKey> {
	public static boolean RUN = true;
	
	public UserPreferencesKeyDbReader(DbResource resource) {
		super(resource);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new FinderRecordProcessor<UserPreferencesKey, UserPreferencesKeyCreator, DbRecord>(new UserPreferencesKeyValidator(), new UserPreferencesKeyDbScanner(), UserPreferencesKeyFactory.get(), new UserPreferencesKeyDbRecordSelector(), new UserPreferencesKeyFinder()));
	}

	public UserPreferencesKeyDbReader(Collection<DbRecord> userPreferencesKeys) {
		super(userPreferencesKeys);
		setup();
	}
		
	@Override
	public boolean isActivated() {
		return RUN;
	}
	@Override
	protected QueryReader getQueryReader() {
		QueryReader queryReader = super.getQueryReader();
		queryReader.addVariable("TableName", UserPreferencesDbReader.TableName);
		return queryReader;
	}
}
