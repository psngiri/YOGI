package yogi.base.app.error;

import java.util.logging.Level;

import yogi.base.app.ErrorReporter;


public class ErrorReporterAdapter<T> {
	public void log(Level level, String message)
	{
		ErrorReporter.get().log(level, message);
	}
	
	public void log(Level level, String message, Throwable e)
	{
		ErrorReporter.get().log(level, message, e);
	}
	
	public void error(String errorMessage)
	{
		ErrorReporter.get().error(errorMessage);
	}
	
	public void error(String header, T item)
	{
		ErrorReporter.get().error(header, item);
	}
	
	public void error(String errorMessage, Throwable e)
	{
		ErrorReporter.get().error((Object)errorMessage, e);
	}
	
	public void error(String header, T item, Throwable e)
	{
		ErrorReporter.get().error(header, item, e);
	}
	
	public void warning(String warningMessage)
	{
		ErrorReporter.get().warning(warningMessage);
	}
	
	public void warning(String header, T item)
	{
		ErrorReporter.get().warning(header, item);
	}

	public void info(String infoMessage)
	{
		ErrorReporter.get().info(infoMessage);
	}
	
	public void info(String header, T item)
	{
		ErrorReporter.get().info(header, item);
	}
	
	public void fine(String header, T item)
	{
		ErrorReporter.get().fine(header, item);
	}

	public void fine(String fineMessage)
	{
		ErrorReporter.get().fine(fineMessage);
	}
	
	public void finer(String header, T item)
	{
		ErrorReporter.get().finer(header, item);
	}

	public void finer(String finerMessage)
	{
		ErrorReporter.get().finer(finerMessage);
	}

	public void finest(String finestMessage)
	{
		ErrorReporter.get().finest(finestMessage);
	}
	
	public void finest(String header, T item)
	{
		ErrorReporter.get().finest(header, item);
	}

}
