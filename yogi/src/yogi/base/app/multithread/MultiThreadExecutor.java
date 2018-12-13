package yogi.base.app.multithread;

import java.util.ArrayList;
import java.util.List;

import yogi.base.app.ActivationState;

public abstract class MultiThreadExecutor<T extends ActivationState> implements ActivationState{
	private List<T> units = new ArrayList<T>();
	
	protected List<T> getUnits() {
		return units;
	}

	protected void addUnit(T unit)
	{
		units.add(unit);
	}
	
	public boolean isActivated() {
		for(T unit: getUnits())
		{
			if(unit.isActivated()) return true;
		}
		return false;
	}

	protected void run() {
		if(ThreadPoolExecutor.NumberOfThreads == 1)
		{
			doSingleThreadExecution();
		}else
		{
			doMultiThreadExecution();
		}
	}
	
	private void doMultiThreadExecution() {
		ThreadPoolExecutor threadPoolExecutor= new ThreadPoolExecutor();
		for(T unit: units)
		{
			if(unit.isActivated())
			{
				threadPoolExecutor.execute(getTask(unit));
			}
		}
		threadPoolExecutor.waitForTaskCompletion();
	}

	private void doSingleThreadExecution() {
		for(T unit: units)
		{
			execute(unit);
		}
	}

	public abstract Task getTask(T unit);
	public abstract void execute(T unit);
}
