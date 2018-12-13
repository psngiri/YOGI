package yogi.server.gui.user.preferences.app.purge;

import yogi.base.app.BaseProcessor;
import yogi.server.gui.user.preferences.UserPreferencesManager;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyManager;

/**
 * @author Vikram Vadavala
 *
 */
public class UserPreferencesPurgeProcessor extends BaseProcessor 
{
	public static boolean RUN = true;
	
	public UserPreferencesPurgeProcessor() {
		super();
	}

	@Override
	public void run() {
		UserPreferencesKeyManager.get().purge();
		UserPreferencesManager.get().purge();
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

}