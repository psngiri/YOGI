package yogi.base.stats;

import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.stats.calculator.Calculator;
import yogi.base.stats.calculator.MultiCalculator;
import yogi.base.stats.memory.MemoryUsageCollector;
import yogi.base.stats.timer.Timer;
import yogi.base.util.logging.Logging;

public class StatsCollector extends BaseCollector {
	private static Logger logger = Logging.getLogger(StatsCollector.class);
	public static Level DefaultLevel = Level.INFO;
	public StatsCollector() {
		this("");
	}

	public StatsCollector(String message) {
		super(message,DefaultLevel, logger, new MultiCalculator());
		addCalculators(Timer.createCalculator(), MemoryUsageCollector.createCalculator());
	}

	@Override
	public MultiCalculator getCalculator() {
		return (MultiCalculator)super.getCalculator();
	}

	public StatsCollector addCalculators(Calculator... calculators)
	{
		MultiCalculator mc = getCalculator();
		for(Calculator calculator: calculators)
		{
			mc.addCalculator(calculator);
		}
		return this;
	}
	
	public static boolean isLoggable(Level level)
	{
		return logger.isLoggable(level);
	}
}
