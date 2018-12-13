package yogi.csi;


public abstract class BaseCSIApplicationStatusUpdater implements CSIApplicaitonStatusUpdater
{
	private Integer exitCode = null;
	
	/* (non-Javadoc)
	 * @see yogi.csi.CSIApplicaitonStatusUpdater#start()
	 */
	public void start()
	{
		Runtime.getRuntime().addShutdownHook(new Thread(new ShutDownHook()));
		updateStatus("Running");
	}
	
	/* (non-Javadoc)
	 * @see yogi.csi.CSIApplicaitonStatusUpdater#exit(int)
	 */
	public void exit(int exitCode)
	{
		if(exitCode < 0)
			updateStatus("Failed");
		else
			updateStatus("Completed");
	}
	
	/* (non-Javadoc)
	 * @see yogi.csi.CSIApplicaitonStatusUpdater#updateStatus(java.lang.String)
	 */
	public abstract void updateStatus(String status);
	
	class ShutDownHook implements Runnable
	{

		public void run() {
			if(exitCode == null)
			{
				updateStatus("Killed");
			}
		}
		
	}
}