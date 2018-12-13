package yogi.base.app;

import yogi.base.util.Timer;

public abstract class TimeWindowPauseLoopChecker extends PauseLoopChecker {

	private long lastExecutionTime = System.currentTimeMillis();		
	private long endExecutionTime = System.currentTimeMillis();
	
	public TimeWindowPauseLoopChecker() {
		super();
	}

	public TimeWindowPauseLoopChecker(Timer timer) {
		super(timer);
	}

	@Override
	public boolean check() {
		boolean rtnValue = super.check();
		lastExecutionTime = endExecutionTime;
		endExecutionTime = System.currentTimeMillis();
		return rtnValue;
	}

	public long getLastExecutionTime() {
		return lastExecutionTime;
	}

	public long getEndExecutionTime() {
		return endExecutionTime;
	}

	public boolean contains(long timestamp)
	{
		return (timestamp >= lastExecutionTime && timestamp < endExecutionTime);
	}
	
	public void reset(long time){
		lastExecutionTime = time;	
		endExecutionTime = time;
	}
}