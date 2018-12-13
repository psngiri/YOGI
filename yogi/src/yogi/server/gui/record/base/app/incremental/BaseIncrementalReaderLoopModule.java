package yogi.server.gui.record.base.app.incremental;

import yogi.base.app.BaseLoopAsynchronousModule;
import yogi.base.app.TimeWindowPauseLoopChecker;
import yogi.base.io.resource.db.DbResource;

public abstract class BaseIncrementalReaderLoopModule extends BaseLoopAsynchronousModule {
	
	public static int sleepTime = 2 * 60 * 1000;
	public static boolean pause;
	
	private DbResource dbResource;

	public BaseIncrementalReaderLoopModule(DbResource dbResource) {
		super();
		this.dbResource = dbResource;
	}

	@Override
	public void setup() {
		MyTimeWindowLoopChecker loopChecker = new MyTimeWindowLoopChecker();
		this.addLoopChecker(loopChecker);
		setup(dbResource, loopChecker);
	}

	protected abstract void setup(DbResource dbResource, TimeWindowPauseLoopChecker loopChecker);


	@Override
	protected void pause() {
		pause = true;
	}
	
	
	private final class MyTimeWindowLoopChecker extends TimeWindowPauseLoopChecker {

		@Override
		protected boolean isPaused() {
			return pause;
		}

		@Override
		protected int getSleepTime() {
			return sleepTime;
		}
	}

}