package yogi.server.gui.record.base.app.incremental;

import yogi.base.Selector;
import yogi.base.app.BaseLoopAsynchronousModule;
import yogi.base.app.PauseLoopChecker;
import yogi.base.app.TimeWindowPauseLoopChecker;
import yogi.base.app.db.SingleConnectionDbModule;
import yogi.base.io.resource.db.DbResource;
import yogi.base.util.Timer;
import yogi.cluster.ClusterManager;
import yogi.server.gui.record.base.BaseRecord;
import yogi.server.gui.record.base.app.incremental.command.IncrementalWriterSinkClusterCommand;
import yogi.server.gui.record.key.Key;


public abstract class BaseIncrementalWriterLoopModule extends BaseLoopAsynchronousModule {
	
	public static int sleepTime = 1 * 60 * 1000;
	public static boolean pause = false;
	public static boolean Active = false;
	public static boolean CheckSystemRestore = true;
	public static boolean BroadcastToCluster = false;
	
	private DbResource dbResource;
	private static MyTimeWindowLoopChecker loopChecker = new MyTimeWindowLoopChecker(new MySystemRestoreTimer());
	
	public BaseIncrementalWriterLoopModule(DbResource dbResource) {
		super();
		this.dbResource = dbResource;
	}
	
	public static void resetLoopChecker(String time){
		resetLoopChecker(Long.parseLong(time));
	}
	
	public static void resetLoopChecker(long time){
		loopChecker.reset(time);
	}
	
	@Override
	public void setup() {
		this.addLoopChecker(loopChecker);
		MySelector selector = new MySelector(loopChecker);
		MyKeySelector keySelector = new MyKeySelector(loopChecker);
		SingleConnectionDbModule singleConnectionDbModule = new MySingleConnectionDbModule(dbResource);
		this.addProcessor(singleConnectionDbModule);
		setup(singleConnectionDbModule, selector, keySelector);
	}

	protected abstract void setup(SingleConnectionDbModule singleConnectionDbModule, Selector<BaseRecord<?>> selector, Selector<Key> keySelector);
		
	@Override
	protected void pause() {
		pause = true;
		loopChecker.reset(loopChecker.getLastExecutionTime());
	}

	public final class MyKeySelector implements Selector<Key> {
		
		private MyTimeWindowLoopChecker loopChecker;
		
		public MyKeySelector(MyTimeWindowLoopChecker loopChecker) {
			super();
			this.loopChecker = loopChecker;
		}
		
		@Override
		public boolean select(Key item) {
			return loopChecker.contains(item.getCreateDate());
		}
	}
	
	public final class MySelector implements Selector<BaseRecord<?>> {
		
		private MyTimeWindowLoopChecker loopChecker;
		
		public MySelector(MyTimeWindowLoopChecker loopChecker) {
			super();
			this.loopChecker = loopChecker;
		}
		
		@Override
		public boolean select(BaseRecord<?> item) {
			return loopChecker.contains(item.getTimeStamp());
		}
	}

	private static class MySystemRestoreTimer extends Timer{

		public MySystemRestoreTimer() {
			super();
		}

		@Override
		protected long getIntervalTime() {
			return PauseLoopChecker.SystemRestoreTime;
		}

		@Override
		protected boolean isActivated() {
			if(Active && !ClusterManager.isCluster()) return false;
			return CheckSystemRestore;
		}


	}
	private static class MyTimeWindowLoopChecker extends TimeWindowPauseLoopChecker {
		
		public MyTimeWindowLoopChecker(Timer timer) {
			super(timer);
		}

		@Override
		protected boolean isPaused() {
			if(pause){
				return pause;
			}else{
				if(Active && !ClusterManager.isCluster()) return true;
			}
			return false;
		}

		@Override
		protected String getRestoreMessage() {
			return "yogi.server.gui.record.base.app.incremental.BaseIncrementalWriterLoopModule:pause";
		}

		@Override
		protected int getSleepTime() {
			return sleepTime;
		}
	}
	
	private static class MySingleConnectionDbModule extends SingleConnectionDbModule{

		public MySingleConnectionDbModule(DbResource dbResource) {
			super(dbResource);
		}

		@Override
		public void run() {
			try {
				super.run();
				if(ClusterManager.isCluster()){
					loopChecker.getEndExecutionTime();
					ClusterManager.get().process(new IncrementalWriterSinkClusterCommand(loopChecker.getEndExecutionTime()));
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}

}