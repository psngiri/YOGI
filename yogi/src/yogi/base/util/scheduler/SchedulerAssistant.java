package yogi.base.util.scheduler;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class SchedulerAssistant {
	private static ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(1);

	public static ScheduledThreadPoolExecutor getScheduler(){
		return scheduler;
	}
}
