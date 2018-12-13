package yogi.base.stats;

import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.app.ErrorReporter;
import yogi.base.stats.calculator.Calculator;

public class BaseCollector implements Collector {
	private String message;
	private Level level;
	private Logger logger;
	private Calculator calculator;
	
	public BaseCollector(String message, Level level, Logger logger, Calculator calculator) {
		super();
		this.message = message;
		this.level = level;
		this.logger = logger;
		this.calculator = calculator;
	}

	public Calculator getCalculator() {
		return calculator;
	}

	public String getMessage() {
		return message;
	}

	public Collector setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Collector start()
	{
		if(logger.isLoggable(level))
		{
			calculator.collect();
			ErrorReporter.get().log(Level.INFO, " Start " + getMessage());
		}
		return this;
	}
	public Collector stop()
	{
		if(logger.isLoggable(level))
		{
			String value = calculator.compute();
			ErrorReporter.get().log(Level.INFO, "Done " + getMessage() + value);
		}
		return this;
	}

}
