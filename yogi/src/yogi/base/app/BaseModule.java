package yogi.base.app;

import java.util.ArrayList;
import java.util.List;

import yogi.base.io.Reader;
import yogi.base.io.Writer;

public abstract class BaseModule extends BaseAppModule implements Module{
	private List<Checker> checkers;
	private List<Processor> processors;
	
	protected BaseModule(List<Checker> checkers, List<Reader<?>> readers, List<Processor> processors, List<Writer> writers) {
		super(readers, writers);
		this.checkers = checkers;
		this.processors = processors;
	}
	
	protected BaseModule() {
		this(new ArrayList<Checker>(), new ArrayList<Reader<?>>(), new ArrayList<Processor>(), new ArrayList<Writer>());
	}
	
	public List<Checker> getRuntimeCheckers()
	{
		return checkers;
	}	
	
	public List<Processor> getProcessors()
	{
		return processors;
	}
	
	public void run() {
		Executor.get().execute(this);
	}
	
	public BaseModule addProcessor(Processor processor)
	{
		this.getProcessors().add(processor);
		return this;
	}

	public BaseModule addRuntimeChecker(Checker checker)
	{
		this.getRuntimeCheckers().add(checker);
		return this;
	}
	
	public BaseModule addInitChecker(Checker checker)
	{
		this.getInitCheckers().add(checker);
		return this;
	}
	
	public BaseModule addInitAndRuntimeChecker(Checker checker) {
		this.getRuntimeCheckers().add(checker);
		this.getInitCheckers().add(checker);
		return this;
	}
	
	public BaseModule addReader(Reader<?> reader)
	{
		this.getReaders().add(reader);
		return this;
	}
	
	public BaseModule addWriter(Writer writer)
	{
		this.getWriters().add(writer);
		return this;
	}
	
	public BaseModule addPropertyFile(String propertyFileName)
	{
		this.getPropertyFiles().add(propertyFileName);
		return this;
	}

}
