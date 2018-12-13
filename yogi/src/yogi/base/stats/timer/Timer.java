package yogi.base.stats.timer;

import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.stats.BaseCollector;
import yogi.base.stats.calculator.Calculator;
import yogi.base.util.logging.Logging;

public class Timer extends BaseCollector {
	private static Logger logger = Logging.getLogger(Timer.class);
	public static Level DefaultLevel = Level.INFO;
	public Timer() {
		this("");
	}

	public Timer(String message) {
		super(message,DefaultLevel, logger, createCalculator());
	}
	
	public static Calculator createCalculator()
	{
		return new TimeCalculator(logger, DefaultLevel); 
	}
}
