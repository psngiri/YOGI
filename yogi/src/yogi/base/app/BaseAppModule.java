package yogi.base.app;

import java.util.ArrayList;
import java.util.List;

import yogi.base.io.Reader;
import yogi.base.io.Writer;

public abstract class BaseAppModule{
	private List<Checker> initCheckers;
	private List<Reader<?>> readers;
	private List<Writer> writers;
	private List<String> propertyFiles;
	
	protected BaseAppModule(List<Reader<?>> readers,List<Writer> writers) {
		super();
		this.readers = readers;
		this.writers = writers;
		this.propertyFiles = new ArrayList<String>();
		this.initCheckers = new ArrayList<Checker>();
	}
	
	protected BaseAppModule() {
		this(new ArrayList<Reader<?>>(),new ArrayList<Writer>());
	}
	
	public boolean isActivated() {
		return true;
	}

	public List<Checker> getInitCheckers()
	{
		return initCheckers;
	}	
	
	public List<Reader<?>> getReaders()
	{
		return readers;
	}
	
	public List<Writer> getWriters()
	{
		return writers;
	}

	public List<String> getPropertyFiles() {
		return propertyFiles;
	}
}
