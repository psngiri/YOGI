package yogi.base.app;

public class MaxAsynchronousModule extends AsynchronousModuleAdapter {
	private long maxTimeInMilliSeconds;
	private boolean completed = false;
	private long startTime;
	
	public MaxAsynchronousModule(Processor processor, long maxTimeInMilliSeconds) {
		super(processor);
		this.maxTimeInMilliSeconds = maxTimeInMilliSeconds;
	}

	@Override
	public void run() {
		completed = false;
		startTime = System.currentTimeMillis();
		long endTime = startTime + maxTimeInMilliSeconds;
		super.run();
		while(!completed && endTime > System.currentTimeMillis()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isCompleted() {
		return completed;
	}

	@Override
	protected void myRun() {
		super.myRun();
		completed = true;
	}

}
