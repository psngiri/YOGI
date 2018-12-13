package yogi.base.app;

import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.app.Checker;
import yogi.base.util.Timer;
import yogi.base.util.logging.Logging;
/**
 * @author Vikram Vadavala
 *
 */
public abstract class PauseLoopChecker implements Checker {
	public static long SystemRestoreTime = 60*60*1000;
	public static boolean CheckSystemRestore = true;
	private Logger logger = Logging.getLogger(PauseLoopChecker.class);
	private Timer timer = new SystemRestoreTimer();
	
	public PauseLoopChecker() {
		super();
	}

	public PauseLoopChecker(Timer timer) {
		super();
		this.timer = timer;
	}

	@Override
	public boolean check(){
		try {
			Thread.sleep(getSleepTime());
			while(isPaused()){
				if(timer.process()){
					restore();
				}
				Thread.sleep(getSleepTime());
			}
			timer.reset();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	protected void restore() {
		logger.log(Level.SEVERE,"System not restored yet for " + getRestoreMessage()+" . Please restore the system as soon as possible");
	}

	protected String getRestoreMessage() {
		return this.getClass().getSimpleName();
	}
	
	protected abstract boolean isPaused(); 

	protected abstract int getSleepTime();

	class SystemRestoreTimer extends Timer{

		public SystemRestoreTimer() {
			super(true);
		}

		@Override
		protected long getIntervalTime() {
			return SystemRestoreTime;
		}

		@Override
		protected boolean isActivated() {
			return CheckSystemRestore;
		}

	}

}
