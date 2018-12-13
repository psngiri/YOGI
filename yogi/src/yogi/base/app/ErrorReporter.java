package yogi.base.app;

import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.app.error.ErrorReporterListener;
import yogi.base.app.error.StackIndexer;
import yogi.base.indexing.ManyIndexer;
import yogi.base.util.logging.Logging;

public class ErrorReporter {
	public static boolean DumpErrorStack = true;
	private StackIndexer errors = new StackIndexer(0);
	private static Logger logger = Logging.getLogger(ErrorReporter.class);
	private ManyIndexer<String, Object> warnings = new ManyIndexer<String, Object>(0);
	private ManyIndexer<String, Object> infoMessages = new ManyIndexer<String, Object>(0);
	private ManyIndexer<String, Object> fineMessages = new ManyIndexer<String, Object>(0);
	private ManyIndexer<String, Object> finerMessages = new ManyIndexer<String, Object>(0);
	private ManyIndexer<String, Object> finestMessages = new ManyIndexer<String, Object>(0);
	private ArrayList<ErrorReporterListener> listeners = new ArrayList<ErrorReporterListener>(1);
	
	private static ThreadLocal<Stack<ErrorReporter>> errorReportersTL = new ThreadLocal<Stack<ErrorReporter>>()
	{
        protected synchronized Stack<ErrorReporter> initialValue() {
            return new Stack<ErrorReporter>();
        }
	};
	
	private static Stack<ErrorReporter> getErrorReporters()
	{
		return errorReportersTL.get();
	}
	
	static boolean isEmpty()
	{
		return ErrorReporter.getErrorReporters().isEmpty();
	}
	
	public static ErrorReporter get()
	{
		return getErrorReporters().peek();
	}
	
	static ErrorReporter push(ErrorReporter errorReporter)
	{
		errorReporter = getErrorReporters().push(errorReporter);
		errorReporter.informListenersAboutPush();
		return errorReporter;
	}
	
	static ErrorReporter pop()
	{
		ErrorReporter errorReporter = getErrorReporters().pop();
		errorReporter.informListenersAboutPop();
		return errorReporter;
	}
	
	static void flush()
	{
		while(!getErrorReporters().isEmpty())
		{
			try {
				pop();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void informListenersAboutPush()
	{
		for(ErrorReporterListener listener: listeners)
		{
			listener.errorReporterPushedToStack(this);
		}
	}
	
	private void informListenersAboutPop()
	{
		for(ErrorReporterListener listener: listeners)
		{
			listener.errorReporterPoppedFromStack(this);
		}
	}
	
	private void informListenersAboutMessage(Level level, Object message, Throwable e)
	{
		for(ErrorReporterListener listener: listeners)
		{
			listener.message(this, level, message, e);
		}
	}
	
	public StackIndexer getErrors() {
		return errors;
	}
	public ManyIndexer<String, Object> getFineMessages() {
		return fineMessages;
	}
	public ManyIndexer<String, Object> getFinerMessages() {
		return finerMessages;
	}
	public ManyIndexer<String, Object> getFinestMessages() {
		return finestMessages;
	}
	public ManyIndexer<String, Object> getInfoMessages() {
		return infoMessages;
	}
	public ManyIndexer<String, Object> getWarnings() {
		return warnings;
	}
	
	public void log(Level level, String message)
	{
		informListenersAboutMessage(level, message, null);
	}
	
	public void log(Level level, String message, Throwable e)
	{
		informListenersAboutMessage(level, message, e);
	}
	
	public void error(Object errorMessage)
	{
		informListenersAboutMessage(Level.SEVERE, errorMessage, null);
	}
	
	public void error(String header, Object item)
	{
		if(logger.isLoggable(Level.SEVERE)) errors.index(header, item);
	}
	
	public void error(Object errorMessage, Throwable e)
	{
		informListenersAboutMessage(Level.SEVERE, errorMessage, e);
	}
	
	public void error(String header, Object item, Throwable e)
	{
		if(logger.isLoggable(Level.SEVERE)) errors.index(header, item, e);
	}
	
	public void warning(Object warningMessage)
	{
		informListenersAboutMessage(Level.WARNING, warningMessage, null);
	}
	
	public void warning(String header, Object item)
	{
		if(logger.isLoggable(Level.WARNING)) warnings.index(header, item);
	}
	
	public void info(Object infoMessage)
	{
		informListenersAboutMessage(Level.INFO, infoMessage, null);
	}
	
	public void info(String header, Object item)
	{
		if(logger.isLoggable(Level.INFO)) infoMessages.index(header, item);
	}
	
	public void fine(Object fineMessage)
	{
		informListenersAboutMessage(Level.FINE, fineMessage, null);
	}
	
	public void fine(String header, Object item)
	{
		if(logger.isLoggable(Level.FINE)) fineMessages.index(header, item);
	}
	
	public void finer(Object finerMessage)
	{
		informListenersAboutMessage(Level.FINER, finerMessage, null);
	}
	
	public void finer(String header, Object item)
	{
		if(logger.isLoggable(Level.FINER)) finerMessages.index(header, item);
	}
	
	public void finest(Object finestMessage)
	{
		informListenersAboutMessage(Level.FINEST, finestMessage, null);
	}
	
	public void finest(String header, Object item)
	{
		if(logger.isLoggable(Level.FINEST)) finestMessages.index(header, item);
	}
	
	public ArrayList<ErrorReporterListener> getListeners() {
		return listeners;
	}
	
	public void addErrorReporterListeners(ArrayList<ErrorReporterListener> listeners)
	{
		this.listeners.addAll(listeners);
	}
	
	public void addErrorReporterListener(ErrorReporterListener listener)
	{
		listeners.add(listener);
	}
	
	public boolean removeErrorReporterListener(ErrorReporterListener listener)
	{
		return listeners.remove(listener);
	}
}
