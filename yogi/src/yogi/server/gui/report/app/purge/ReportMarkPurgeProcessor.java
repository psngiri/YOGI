package yogi.server.gui.report.app.purge;

import yogi.base.app.BaseProcessor;
import yogi.server.gui.record.purge.RecordPurgeAssistant;
import yogi.server.gui.report.ReportManager;
import yogi.server.gui.report.key.ReportKeyManager;

/**
 * @author Vikram Vadavala
 *
 */
public class ReportMarkPurgeProcessor extends BaseProcessor 
{
	public static boolean RUN = true;
	private long timestamp;
	
	protected ReportMarkPurgeProcessor(long timestamp) {
		super();
		this.timestamp = timestamp;
	}

	@Override
	public void run() {
		RecordPurgeAssistant.get().markForPurge(ReportKeyManager.get(),ReportManager.get(), timestamp);
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}
	


}