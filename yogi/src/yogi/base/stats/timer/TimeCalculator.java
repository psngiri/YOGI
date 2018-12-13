package yogi.base.stats.timer;

import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.stats.calculator.Calculator;

public class TimeCalculator implements Calculator{
	private long startTime;
	private Logger logger;
	private Level level;

	
	protected TimeCalculator(Logger logger, Level level) {
		super();
		this.logger = logger;
		this.level = level;
	}

	public String compute() {
		String value = null;
		if(logger.isLoggable(level))
		{
			long timespent = System.nanoTime() - startTime;
			float t = timespent;
			t = t/1000000000;
			value = " Execution time in Seconds: " + t;
		}
		return value;
	}

	public void collect() {
		if(logger.isLoggable(level))
		{
			startTime = System.nanoTime();
		}
	}

}
