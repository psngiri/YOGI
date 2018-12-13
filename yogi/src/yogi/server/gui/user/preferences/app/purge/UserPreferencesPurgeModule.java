package yogi.server.gui.user.preferences.app.purge;
/**
 * @author Vikram Vadavala
 *
 */
import yogi.base.io.resource.db.DbResource;
import yogi.server.base.purge.io.db.PurgeDbORDbFileWriter;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.record.app.purge.RecordPurgeModule;
import yogi.server.gui.record.key.KeyManager;
import yogi.server.gui.user.preferences.UserPreferences;
import yogi.server.gui.user.preferences.UserPreferencesData;
import yogi.server.gui.user.preferences.UserPreferencesManager;
import yogi.server.gui.user.preferences.io.db.purge.UserPreferencesPurgeDbWriter;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyManager;

public class UserPreferencesPurgeModule extends RecordPurgeModule<UserPreferencesKey, UserPreferencesData, UserPreferences> {
	
	public static boolean RUN = true;

	public UserPreferencesPurgeModule(DbResource dbResource, long timestamp) {
		super(dbResource, timestamp);
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	@Override
	protected PurgeDbORDbFileWriter<UserPreferences> getWriter(DbResource dbResource) {
		return new UserPreferencesPurgeDbWriter(dbResource);
	}

	@Override
	protected RecordManager<UserPreferencesKey, UserPreferencesData, UserPreferences> getRecordManager() {
		return UserPreferencesManager.get();
	}

	@Override
	protected KeyManager<UserPreferencesKey> getKeyManager() {
		return UserPreferencesKeyManager.get();
	}
}
