package yogi.server.gui.report.app.purge;

import yogi.base.app.BaseProcessor;
import yogi.server.gui.report.ReportManager;
import yogi.server.gui.report.key.ReportKeyManager;

/**
 * @author Vikram Vadavala
 *
 */
public class ReportPurgeProcessor extends BaseProcessor 
{
	public static boolean RUN = true;
	
	public ReportPurgeProcessor() {
		super();
	}

	@Override
	public void run() {
		ReportKeyManager.get().purge();
		ReportManager.get().purge();
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

}