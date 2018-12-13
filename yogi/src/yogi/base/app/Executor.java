package yogi.base.app;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.app.error.ErrorReporterAdapter;
import yogi.base.app.error.ErrorReporterLogListener;
import yogi.base.app.multithread.Callable;
import yogi.base.app.multithread.rdc.RDCCallable;
import yogi.base.factory.capacity.io.FactoryCapacityReader;
import yogi.base.factory.capacity.io.FactoryCapacityWriter;
import yogi.base.io.Reader;
import yogi.base.io.Writer;
import yogi.base.relationship.types.capacity.io.CapacityReader;
import yogi.base.relationship.types.capacity.io.CapacityWriter;
import yogi.base.relationship.types.io.RelationshipTypeWriter;
import yogi.base.stats.Collector;
import yogi.base.stats.StatsCollector;
import yogi.base.util.LockManager;
import yogi.base.util.logging.Logging;
import yogi.base.util.logging.LoggingPropertiesFileReader;
import yogi.property.alias.io.PropertyAliasReader;
import yogi.property.io.PropertyReader;

public class Executor extends ErrorReporterAdapter<String>{
	private Logger logger = Logging.getLogger(Executor.class);
	public static boolean Pause = false;
	public static boolean Cancel = false;
	public static boolean HoldOffCancel = false;
	private static Executor executor = new Executor();
	private ErrorReporterLogListener errorReporterLogListener = new ErrorReporterLogListener();
	private int exitCode;
	private String exitMessage;
	public static Level callableLevel=Level.FINE;
	public static Level checkerLevel=Level.INFO;
	public static Level commandLevel=Level.INFO;
	
	protected Executor() {
		super();
	}

	public static Executor get() {
		return executor;
	}
	
	public ErrorReporterLogListener getErrorReporterLogListener() {
		return errorReporterLogListener;
	}

	public void setErrorReporterLogListener(ErrorReporterLogListener errorReporterLogListener) {
		this.errorReporterLogListener = errorReporterLogListener;
	}

	public void execute(Application application)
	{
		exitCode = 0;
		LockManager lockManager = new LockManager(ApplicationProperties.OutputLocation, application.getClass().getSimpleName());
		if(!application.isActivated()) return;
		readLoggingPropertiesFile();
		startExecutionRecording();
		Collector timer = new StatsCollector("Executing Application: " + application.getClass().getSimpleName()).start();
		try {
			readPropertyAliases();
			readCapacities();
			setup(application);
			loadPropertyFiles(application);
			lockManager.checkAndCreateLock();
			application.start();
			read(application.getReaders());
			if(executeInitCheckers(application))
			{
				executeModules(application.getModules());
				write(application.getWriters());
				writeSystemConfigFiles();
			}
			if ( exitCode != 0 ) {
				logger.log(Level.WARNING, "Application: " + application.getClass().getName() + " message: Exit code " + this.exitCode + " Message " + this.exitMessage);
			}
		} catch (CancelException e) {
			info(e.getMessage());
			exitCode = 1;
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			exitCode = -1;
		}catch (Throwable e) {
			logger.log(Level.SEVERE, "Error in Application: " + application.getClass().getName() + " message: " + e.getMessage(), e);
			exitCode = -1;
		}finally
		{
			timer.stop();
			endExecutionRecording();
			ErrorReporter.flush();
			lockManager.removeLock();
		}
		application.exit(exitCode);
	}

	public void writeSystemConfigFiles() {
		writeCapacities();
		writeRelationships();
	}

	public int getExitCode() {
		return exitCode;
	}

	public void setExitCode(int exitCode) {
		this.exitCode = exitCode;
	}

	private boolean executeInitCheckers(Application application) {
		List<Checker> initCheckers = new ArrayList<Checker>();
		if ( application.isActivated() ) {
			initCheckers.addAll(application.getInitCheckers());
		}
		for(Module module: application.getModules())
		{
			if ( module.isActivated() ) {
				collectAppCheckers(initCheckers, module);
			}
		}
		return check(initCheckers);
	}
	
	private void collectAppCheckers(List<Checker> appCheckers, Module module)
	{
		appCheckers.addAll(module.getInitCheckers());
		for(Processor processor: module.getProcessors())
		{
			if(processor instanceof Module && processor.isActivated() ) {
				collectAppCheckers(appCheckers, ((Module)processor));
			}
		}
	}

	protected void endExecutionRecording() {
		ErrorReporter.pop();
	}

	protected void startExecutionRecording() {
		ErrorReporter errorReporter = new ErrorReporter();
		startExecutionRecording(errorReporter);
	}

	protected void startExecutionRecording(ErrorReporter errorReporter) {
		ErrorReporter lastErrorReporter = null;
		if(!ErrorReporter.isEmpty()) lastErrorReporter = ErrorReporter.get();
		errorReporter = ErrorReporter.push(errorReporter);
		if(lastErrorReporter == null)
		{
			errorReporter.addErrorReporterListener(errorReporterLogListener);
		}else
		{
			errorReporter.addErrorReporterListeners(lastErrorReporter.getListeners());
		}
		executionHook();
	}

	private void executionHook() {
		boolean paused = false;
		if(Pause) {
			paused = true;
			info("Application paused execution due to a Pause Request");
		}
		while(Pause && !Cancel)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		if(paused) info("Application resumed execution due to a Pause Request");

		if(Cancel && !HoldOffCancel) throw new CancelException("Application stopped execution due to a Cancel Request");
	}

	public void execute(Module module)
	{
		if(!module.isActivated()) return;
		startExecutionRecording();
		Collector timer = new StatsCollector("Executing Module: " + module.getClass().getSimpleName()).start();
		try {
			if(check(module.getRuntimeCheckers()))
			{
				read(module.getReaders());
				executeProcessors(module.getProcessors());
				write(module.getWriters());
			}
		} catch (RuntimeException e) {
			info("Error in Module: " + module.getClass().getName() + " message: " + e.getMessage());
			throw e;
		}finally
		{
			timer.stop();
			endExecutionRecording();
		}
	}
	
	public void execute(Processor processor)
	{
		if(!processor.isActivated()) return;
		startExecutionRecording();
		Collector timer = new StatsCollector("Executing Processor: " + processor.getClass().getSimpleName()).start();
		try {
			processor.run();
		} catch (RuntimeException e) {
			info("Error in Processor: " + processor.getClass().getName() + " message: " + e.getMessage());
			throw e;
		}finally
		{
			timer.stop();
			endExecutionRecording();
		}
	}

	public <R> R execute(Command<R> command)
	{
		R rtnValue = null;
		startExecutionRecording();
		Collector timer=null;
		if (StatsCollector.isLoggable(commandLevel)) {
			timer = new StatsCollector("Executing Command: "+ command.getClass().getSimpleName()).start();
		}
		try {
			rtnValue = command.execute();
		} catch (RuntimeException e) {
			info("Error in Command: " + command.getClass().getName() + " message: " + e.getMessage());
			throw e;
		}finally{
			if (timer!=null) {
				timer.stop();
			}
			endExecutionRecording();
		}
		return rtnValue;
	}

	public <T> List<T> execute(Reader<T> reader)
	{
		if(!reader.isActivated()) return new ArrayList<T>();
		startExecutionRecording();
		Collector timer = new StatsCollector("Reading: " + reader.getClass().getSimpleName()).start();
		try {
			return reader.read();
		} catch (RuntimeException e) {
			info("Error in Reader: " + reader.getClass().getName() + " message: " + e.getMessage());
			throw e;
		}finally
		{
			timer.stop();
			endExecutionRecording();
		}
	}
	
	public void execute(Writer writer)
	{
		if(!writer.isActivated()) return;
		startExecutionRecording();
		Collector timer = new StatsCollector("Writing: " + writer.getClass().getSimpleName()).start();
		try {
			writer.write();
		} catch (RuntimeException e) {
			info("Error in Writer: " + writer.getClass().getName() + " message: " + e.getMessage());
			throw e;
		}finally
		{
			timer.stop();
			endExecutionRecording();
		}
	}
	
	public <T> void execute(Callable<T> callable, T item)
	{
		Collector timer=null;
		if(StatsCollector.isLoggable(callableLevel)) {
			startExecutionRecording();
			timer = new StatsCollector("Calling: " + callable.getClass().getSimpleName() + " for " + item.getClass().getSimpleName()+ ": " + item).start();
			}
		try {
			callable.call(item);
		} catch (RuntimeException e) {
			info("Error in Callable: " + callable.getClass().getName() + " for item: "+ item + " message: " + e.getMessage());
			throw e;
		}finally
		{
			if(timer !=null) {
				timer.stop();
			}
			if(StatsCollector.isLoggable(callableLevel)) {
				endExecutionRecording();
			}
		}
	}
	
	public <T, R> R execute(RDCCallable<T,R> callable, T item)
	{
		Collector timer=null;
		if(StatsCollector.isLoggable(callableLevel)) {
			startExecutionRecording();
			timer = new StatsCollector("Calling: " + callable.getClass().getSimpleName() + " for " + item.getClass().getSimpleName()+ ": " + item).start();
			}
		try {
			return callable.call(item);
		} catch (RuntimeException e) {
			info("Error in Callable: " + callable.getClass().getName() + " for item: "+ item + " message: " + e.getMessage());
			throw e;
		}finally
		{
			if(timer !=null) {
				timer.stop();
			}
			if(StatsCollector.isLoggable(callableLevel)) {
				endExecutionRecording();
			}
		}
	}

	private boolean execute(Checker checker) {
		boolean rtnValue = true;
		startExecutionRecording();
		Collector timer=null;
		if(StatsCollector.isLoggable(checkerLevel)) {
			timer = new StatsCollector("Checking: "+ checker.getClass().getSimpleName()).start();
		}
		try {
			rtnValue = checker.check();
		} catch (RuntimeException e) {
			info("Error in Checker: " + checker.getClass().getName() + " message: " + e.getMessage());
			throw e;
		}finally
		{
			if (timer!=null) {
				timer.stop();
			}
			endExecutionRecording();
		}
		return rtnValue;
	}

	public boolean check(List<Checker> checkers) {
		for(Checker checker: checkers)
		{
			if(!execute(checker)) return false;
		}
		return true;
	}
	private void read(List<Reader<?>> readers) {
		for(Reader<?> reader: readers)
		{
			execute(reader);
		}
	}

	private void executeModules(List<Module> modules) {
		for(Module module: modules)
		{
			module.run();
		}
	}

	private void executeProcessors(List<Processor> processors) {
		for(Processor processor: processors)
		{
			execute(processor);
		}
	}

	private void write(List<Writer> writers) {
		for(Writer writer: writers)
		{
			execute(writer);
		}
	}
	
	private void setup(Application application)
	{
		application.setup();
		for(Module module: application.getModules())
		{
			setup(module);
		}
	}
	
	private void loadPropertyFiles(Application application)
	{
		startExecutionRecording();
		for(Module module: application.getModules())
		{
			loadPropertyFiles(module);
		}
		loadPropertyFiles(application.getPropertyFiles());
		endExecutionRecording();
	}
	
	private void loadPropertyFiles(List<String> propertyFiles) {
		for(String file: propertyFiles)
		{
			loadPropertyFile(file);
		}
	}

	protected void loadPropertyFile(String file) {
		try {
			new PropertyReader(file).read();
		} catch (RuntimeException e) {
			ErrorReporter.get().warning(e.getMessage());
		}
	}

	public void setup(Module module)
	{
		module.setup();
		for(Processor processor: module.getProcessors())
		{
			if(processor instanceof Module) setup(((Module)processor));
		}
	}
	
	private void loadPropertyFiles(Module module)
	{
		for(Processor processor: module.getProcessors())
		{
			if(processor instanceof Module) loadPropertyFiles(((Module)processor));
		}
		loadPropertyFiles(module.getPropertyFiles());
	}
	
	protected void readCapacities() {
		execute(new CapacityReader());
		execute(new FactoryCapacityReader());
	}

	protected void readPropertyAliases() {
		execute(new PropertyAliasReader());
	}

	private void writeRelationships() {
		execute(new RelationshipTypeWriter());
	}

	private void writeCapacities() {
		execute(new CapacityWriter());
		execute(new FactoryCapacityWriter());
	}

	protected void readLoggingPropertiesFile() {
		execute(new LoggingPropertiesFileReader());
	}

	public String getExitMessage() {
		return exitMessage;
	}

	public void setExitMessage(String exitMessage) {
		this.exitMessage = exitMessage;
	}

	
}

class CancelException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public CancelException() {
		super();
	}

	public CancelException(String message, Throwable cause) {
		super(message, cause);
	}

	public CancelException(String message) {
		super(message);
	}

	public CancelException(Throwable cause) {
		super(cause);
	}
	
}