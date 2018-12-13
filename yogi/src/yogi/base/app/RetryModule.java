package yogi.base.app;

import java.util.List;

import yogi.base.io.Reader;
import yogi.base.io.Writer;


public abstract class RetryModule extends BaseModule {
	public static int RetryAttempts = 3;

	public RetryModule() {
		super();
	}

	public RetryModule(List<Checker> checkers, List<Reader<?>> readers, List<Processor> processors, List<Writer> writers, List<Checker> loopCheckers) {
		super(checkers, readers, processors, writers);
	}

	@Override
	public void run() {
		int attempts = 1;
		boolean retry = retryExecution(attempts);
		while(retry && attempts < RetryAttempts)
		{
			attempts ++;
			ErrorReporter.get().info(String.format("Trying %s attempt for %s", attempts, this.getClass()));
			retry = retryExecution(attempts);
		}
		if(attempts == RetryAttempts) ErrorReporter.get().error("Could not process even after " +  RetryAttempts + " retry attempts", this.getClass());
	}
	
	private boolean retryExecution(int attempt){
		try {
			super.run();
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
