package yogi.base.util;


public class Timer {
	private long lastExecutionTime = 0;
	public static long IntervalTimeInMillSeconds = 60*60*1000;
	private boolean dontShowFirstTime = false;
	
	public Timer() {
		super();
	}
	
	public Timer(boolean dontShowFirstTime) {
		super();
		this.dontShowFirstTime = dontShowFirstTime;
	}

	public boolean process(){
		if(!isActivated()) return false;
		if(dontShowFirstTime && lastExecutionTime == 0) lastExecutionTime = System.currentTimeMillis();
		if(System.currentTimeMillis() - lastExecutionTime > getIntervalTime()){
			lastExecutionTime = System.currentTimeMillis();
			return true;
		}
		return false;
	}
	
	public void reset(){
		lastExecutionTime = 0;
	}
	
	protected boolean isActivated(){
		return true;
	}
	
	protected long getIntervalTime(){
		return IntervalTimeInMillSeconds;
	}

}
