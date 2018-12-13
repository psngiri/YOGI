package yogi.remote.log;

import java.io.Serializable;
import java.util.logging.Level;

public class LogRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5899031507189625143L;
	private Level level;
	private String message;
	private Throwable throwable;
	public LogRecord(Level level, String message, Throwable throwable) {
		super();
		this.level = level;
		this.message = message;
		this.throwable = throwable;
	}
	public Level getLevel() {
		return level;
	}
	public String getMessage() {
		return message;
	}
	public Throwable getThrowable() {
		return throwable;
	}
	
}
