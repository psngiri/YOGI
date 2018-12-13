package yogi.server.gui.user.preferences.io.db;

import java.util.Collection;

import yogi.base.io.db.DbRecord;
import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.record.io.db.RecordDbReader;
import yogi.server.gui.user.preferences.UserPreferences;
import yogi.server.gui.user.preferences.UserPreferencesCreator;
import yogi.server.gui.user.preferences.UserPreferencesData;
import yogi.server.gui.user.preferences.UserPreferencesFactory;
import yogi.server.gui.user.preferences.UserPreferencesValidator;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyManager;

public class UserPreferencesDbReader extends RecordDbReader<UserPreferencesKey,UserPreferencesData,UserPreferences, UserPreferencesCreator> {
	public static boolean RUN = true;
	public static String TableName = "Strategies";

	public UserPreferencesDbReader(DbResource resource) {
		super(resource, UserPreferencesKeyManager.get(), UserPreferencesFactory.get(), new UserPreferencesDbScanner(), new UserPreferencesValidator());
	}

	public UserPreferencesDbReader(Collection<DbRecord> carrierSpecificFilters) {
		super(carrierSpecificFilters, UserPreferencesKeyManager.get(), UserPreferencesFactory.get(), new UserPreferencesDbScanner(), new UserPreferencesValidator());
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
	
	@Override
	protected QueryReader getQueryReader() {
		QueryReader queryReader = super.getQueryReader();
		queryReader.addVariable("TableName", TableName);
		return queryReader;
	}

}
