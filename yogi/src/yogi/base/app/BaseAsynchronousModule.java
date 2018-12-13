package yogi.base.app;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.io.Reader;
import yogi.base.io.Writer;
import yogi.base.util.logging.Logging;


public abstract class BaseAsynchronousModule extends BaseModule {
	private Logger logger = Logging.getLogger(BaseAsynchronousModule.class);
	
	protected BaseAsynchronousModule() {
		super();
	}

	protected BaseAsynchronousModule(List<Checker> checkers, List<Reader<?>> readers, List<Processor> processors, List<Writer> writers) {
		super(checkers, readers, processors, writers);
	}

	@Override
	public void run() {
		new Thread(new Runnable(){
			public void run() {
				myRun();
		}
		}).start();
	}
	
	protected void myRun()
	{
		try 
		{
			super.run();
		} catch (Throwable t) {
			String msg = getMessage();
			logger.log(Level.SEVERE,msg + " due to Exception " + t.getMessage(),t);
		}
	}

	protected String getMessage() {
		String msg = "Error in Module " + this.getClass().getSimpleName();
		return msg;
	}
	
}
