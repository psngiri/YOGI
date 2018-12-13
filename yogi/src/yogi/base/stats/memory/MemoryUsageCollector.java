package yogi.base.stats.memory;

import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.stats.BaseCollector;
import yogi.base.stats.calculator.Calculator;
import yogi.base.util.logging.Logging;

public class MemoryUsageCollector extends BaseCollector {
	private static Logger logger = Logging.getLogger(MemoryUsageCollector.class);
	public static Level DefaultLevel = Level.INFO;
	public MemoryUsageCollector() {
		this("");
	}

	public MemoryUsageCollector(String message) {
		super(message,DefaultLevel, logger, createCalculator());
	}
	
	public static Calculator createCalculator()
	{
		return new MemoryUsageCalculator(logger, DefaultLevel); 
	}

}
