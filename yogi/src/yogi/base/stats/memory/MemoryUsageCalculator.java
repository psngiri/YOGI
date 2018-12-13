package yogi.base.stats.memory;

import java.lang.management.ManagementFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.stats.calculator.Calculator;
import yogi.remote.server.app.CommandServerProcessor;

public class MemoryUsageCalculator implements Calculator{
	private static final int M = 1048576;
	private Logger logger;
	private Level level;
	private long heapUsedStart;
	private long nonHeapUsedStart;
	public static long MinimumFreeMemoryInMb=20000L;
	public static boolean checkForMinimumFreeHeapSize=false;
	
	protected MemoryUsageCalculator(Logger logger, Level level) {
		super();
		this.logger = logger;
		this.level = level;
	}
	
	public String compute() {
		String value = null;
		if(checkForMinimumFreeHeapSize) checkForFreeMemory();
		if(logger.isLoggable(level))
		{
			long heapUsed = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
			long heapIncreased = heapUsed - heapUsedStart;
			float hUM = heapIncreased;
			hUM = hUM/M;
			long nonHeapUsed = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed();
			long nonHeapIncreased = nonHeapUsed - nonHeapUsedStart;
			float nHUM = nonHeapIncreased;
			nHUM = nHUM/M;
			value = " heap size " + heapUsed/M + "M increased by " + hUM + "M non heap size " + nonHeapUsed/M + "M increased by " + nHUM + "M";
		}
		return value;
	}
	
	public void collect() {
		if(logger.isLoggable(level))
		{
			heapUsedStart = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
			nonHeapUsedStart = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed();
		}
	}
	
	private void checkForFreeMemory(){
		long currentHeapSize = (ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed()/M);
		long maxHeapSize=(ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax()/M);
		if((maxHeapSize-currentHeapSize) <= MinimumFreeMemoryInMb) {
			logger.severe("Server at port "+CommandServerProcessor.RegistryPort+" Current Heap Size "+currentHeapSize+" is Nearing its Maximum Limit "+maxHeapSize);
			checkForMinimumFreeHeapSize=false;
		}
	}

}
