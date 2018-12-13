package yogi.server.gui.user.preferences.io.db;

import yogi.base.io.db.QueryReader;
import yogi.server.gui.record.io.db.RecordDbFormatter;
import yogi.server.gui.user.preferences.UserPreferences;
import yogi.server.gui.user.preferences.UserPreferencesData;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;


public class UserPreferencesDbFormatter extends RecordDbFormatter<UserPreferencesKey,UserPreferencesData,UserPreferences> {
	@Override
	protected QueryReader getQueryReader() {
		QueryReader queryReader = super.getQueryReader();
		queryReader.addVariable("TableName", UserPreferencesDbReader.TableName);
		return queryReader;
	}

}