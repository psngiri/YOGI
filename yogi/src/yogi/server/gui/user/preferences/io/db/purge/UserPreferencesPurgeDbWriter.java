package yogi.server.gui.user.preferences.io.db.purge;

import yogi.base.io.resource.db.DbResource;
import yogi.server.base.purge.io.db.PurgeDbORDbFileWriter;
import yogi.server.gui.record.purge.db.RecordPurgeDbFormatter;
import yogi.server.gui.user.preferences.UserPreferences;
import yogi.server.gui.user.preferences.UserPreferencesFactory;
import yogi.server.gui.util.GuiAssistant;

public class UserPreferencesPurgeDbWriter extends PurgeDbORDbFileWriter<UserPreferences> {
	
	public static String Type="UserPreferences";
	
	public UserPreferencesPurgeDbWriter(DbResource resource){
		super(resource, UserPreferencesFactory.get(), new RecordPurgeDbFormatter<UserPreferences>(Type), UserPreferences.class, GuiAssistant.PurgeDeleteDir);
	}
	
}
