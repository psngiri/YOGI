package yogi.server.gui.user.preferences.app.purge;

import yogi.base.app.BaseProcessor;
import yogi.server.gui.record.purge.RecordPurgeAssistant;
import yogi.server.gui.user.preferences.UserPreferencesManager;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyManager;

/**
 * @author Vikram Vadavala
 *
 */
public class UserPreferencesMarkPurgeProcessor extends BaseProcessor 
{
	public static boolean RUN = true;
	private long timestamp;
	
	protected UserPreferencesMarkPurgeProcessor(long timestamp) {
		super();
		this.timestamp = timestamp;
	}

	@Override
	public void run() {
		RecordPurgeAssistant.get().markForPurge(UserPreferencesKeyManager.get(),UserPreferencesManager.get(), timestamp);
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}
	


}