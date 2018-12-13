package yogi.base.app.multithread;

import java.util.ArrayList;
import java.util.List;

import yogi.base.app.Processor;

public class MultiThreadExecutorProcessor<T extends BaseTask> implements Processor{
	private List<T> units = new ArrayList<T>();
	private Integer numberOfThreads;

	public MultiThreadExecutorProcessor() {
		super();
	}

	public MultiThreadExecutorProcessor(Integer numberOfThreads) {
		super();
		this.numberOfThreads = numberOfThreads;
	}

	public List<T> getUnits() {
		return units;
	}

	public void addUnit(T unit)
	{
		units.add(unit);
	}
	
	private Integer getNumberOfThreads() {
		if(numberOfThreads == null){
			int size = units.size();
			return Math.min(size, ThreadPoolExecutor.NumberOfThreads);
		}
		return numberOfThreads;
	}

	public void run() {
		int nt = getNumberOfThreads();
		if(nt == 1)
		{
			doSingleThreadExecution();
		}else
		{
			doMultiThreadExecution(nt);
		}
	}
	
	private void doMultiThreadExecution(int nt) {
		ThreadPoolExecutor threadPoolExecutor= new ThreadPoolExecutor(nt);
		for(T unit: units)
		{
			threadPoolExecutor.execute(getTask(unit));
		}
		threadPoolExecutor.waitForTaskCompletion();
	}


	private void doSingleThreadExecution() {
		for(T unit: units)
		{
			execute(unit);
		}
	}

	public Task getTask(T unit){
		return unit;
	}
	
	public void execute(T unit){
		unit.run();
	}

	@Override
	public boolean isActivated() {
		return true;
	}
}
