package examples.server.fare.app.purge;

import yogi.base.app.BaseProcessor;

import examples.server.fare.FareAssistant;
import examples.server.fare.FareManager;
import examples.server.fare.farekey.FareKeyManager;
import examples.server.sub.Sub;

/**
 * @author Vikram Vadavala
 *
 */
public class FareMarkPurgeProcessor extends BaseProcessor 
{
	public static boolean RUN = true;
	private Sub sub;
	
	protected FareMarkPurgeProcessor(Sub sub) {
		super();
		this.sub = sub;
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
	
	@Override
	public void run() {
		FareAssistant.get().markForPurge(FareKeyManager.get(),FareManager.get(), sub);
	}

}