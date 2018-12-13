package yogi.server.gui.user.preferences.app;

import yogi.base.app.BaseModule;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.user.preferences.io.db.UserPreferencesDbReader;
import yogi.server.gui.user.preferences.key.io.db.UserPreferencesKeyDbReader;

public class UserPreferencesReaderModule extends BaseModule {
	public static boolean RUN = true;
	private DbResource ngpDbResource;
	
	public UserPreferencesReaderModule(DbResource ngpDbResource) {
		super();
		this.ngpDbResource=ngpDbResource;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		addReader(new UserPreferencesKeyDbReader(ngpDbResource));
		addReader(new UserPreferencesDbReader(ngpDbResource));
	}

}
