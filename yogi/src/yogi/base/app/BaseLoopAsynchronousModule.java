package yogi.base.app;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.io.Reader;
import yogi.base.io.Writer;
import yogi.base.util.logging.Logging;


public abstract class BaseLoopAsynchronousModule extends BaseModule {
	private List<Checker> loopCheckers;
	private Logger logger = Logging.getLogger(BaseLoopAsynchronousModule.class);
	
	protected BaseLoopAsynchronousModule() {
		super();
		this.loopCheckers = new ArrayList<Checker>();
	}

	protected BaseLoopAsynchronousModule(List<Checker> checkers, List<Reader<?>> readers, List<Processor> processors, List<Writer> writers, List<Checker> loopCheckers) {
		super(checkers, readers, processors, writers);
		this.loopCheckers = loopCheckers;
	}

	public List<Checker> getLoopCheckers() {
		return loopCheckers;
	}

	public BaseLoopAsynchronousModule addLoopChecker(Checker loopCchecker) {
		this.loopCheckers.add(loopCchecker);
		return this;
	}
	
	@Override
	public void run() {
		new Thread(new MyRunnable(this)).start();
	}
	
	private void myRun()
	{
		try {
			while(Executor.get().check(getLoopCheckers()))
			{
				super.run();
			}
		} catch (Throwable t) {
			pause();
			logger.log(Level.SEVERE,"Pausing " + this.getClass().getSimpleName()+" due to Exception "+t.getMessage(),t);
		}
	}
	
	protected abstract void pause();
	
	private static class MyRunnable implements Runnable
	{
		private BaseLoopAsynchronousModule module;
		
		public MyRunnable(BaseLoopAsynchronousModule module) {
			super();
			this.module = module;
		}

		@Override
		public void run() {
			while(true){
				module.myRun();
			} 
		}
		
	} 
}
