package examples.server.fare.app.purge;

import yogi.base.app.BaseProcessor;

import examples.server.fare.FareManager;
import examples.server.fare.farekey.FareKeyManager;

/**
 * @author Vikram Vadavala
 *
 */
public class FarePurgeProcessor extends BaseProcessor 
{
	public static boolean RUN = true;
	
	public FarePurgeProcessor() {
		super();
	}

	@Override
	public void run() {
		FareKeyManager.get().purge();
		FareManager.get().purge();
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

}