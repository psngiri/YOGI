package yogi.server.gui.user.preferences.io.db;

import yogi.base.Selector;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.record.io.db.RecordDbWriter;
import yogi.server.gui.user.preferences.UserPreferences;
import yogi.server.gui.user.preferences.UserPreferencesData;
import yogi.server.gui.user.preferences.UserPreferencesManager;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;

public class UserPreferencesDbWriter extends RecordDbWriter<UserPreferencesKey,UserPreferencesData,UserPreferences>
{	
		public UserPreferencesDbWriter(DbResource resource, Selector<? super UserPreferences> selector){		
			super(resource, selector, new UserPreferencesDbFormatter(), UserPreferencesManager.get());
		}
		
		public UserPreferencesDbWriter(DbResource resource) {
			this(resource, null);
		}
}

