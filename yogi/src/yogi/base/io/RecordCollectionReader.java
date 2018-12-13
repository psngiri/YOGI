package yogi.base.io;

import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.io.resource.Resource;
import yogi.base.util.logging.Logging;

public class RecordCollectionReader<T> implements RecordReader<T> {
	Logger logger = Logging.getLogger(RecordCollectionReader.class);
	private Iterator<T> reader;
	private T record;
	private Collection<T> data;
	private Resource resource = new MyResource();
	
	public RecordCollectionReader(Collection<T> data) {
		super();
		this.data = data;
	}
	
	public boolean close() {
		return true;
	}

	public boolean hasNext() {
		if(record != null) return true;
		if(reader.hasNext()) {
			record = reader.next();
			return true;
		}
		return false;
	}

	public T next() {
		if(logger.isLoggable(Level.FINEST))logger.finest("Processing Record:" + record);
		T rtnValue = record;
		record = null;
		return rtnValue;
	}

	public boolean open() {
		reader = data.iterator();
		return true;
	}

	public Resource getResource() {
		return resource;
	}

	static class MyResource implements Resource{
		public String getName() {
			return "String Collection";
		}
	}

}
