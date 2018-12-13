package yogi.base.app.multithread;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMultiThreadExecutor<T>{
	private List<T> units = new ArrayList<T>();
	
	protected List<T> getUnits() {
		return units;
	}

	protected void addUnit(T unit)
	{
		units.add(unit);
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
			if(isActivated(unit))
			{
				threadPoolExecutor.execute(getTask(unit));
			}
		}
		threadPoolExecutor.waitForTaskCompletion();
	}

	protected abstract boolean isActivated(T unit);

	private void doSingleThreadExecution() {
		for(T unit: units)
		{
			execute(unit);
		}
	}

	public abstract Task getTask(T unit);
	public abstract void execute(T unit);
}
