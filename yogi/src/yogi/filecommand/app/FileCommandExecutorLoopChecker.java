package yogi.filecommand.app;

import yogi.base.app.Checker;
/**
 * @author Vikram Vadavala
 *
 */
public class FileCommandExecutorLoopChecker implements Checker {
	public static boolean paused=false;
	public static int SleepTime = 60000;
	
	@Override
	public boolean check(){
		try {
			Thread.sleep(SleepTime);
			while(isPaused()){
				Thread.sleep(SleepTime);
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return true;
	}
	
	public void setPaused(boolean paused) {
		FileCommandExecutorLoopChecker.paused = paused;
	}
	
	public boolean isPaused() {
		return paused;
	}
}
