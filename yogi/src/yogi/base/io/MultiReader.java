package yogi.base.io;

import java.util.ArrayList;
import java.util.List;


public class MultiReader<T> implements Reader<T> {
	private List<Reader<? extends T>> readers = new ArrayList<Reader<? extends T>>();
	
	public MultiReader() {
		super();
	}

	public List<Reader<? extends T>> getReaders() {
		return readers;
	}

	public void addReader(Reader<? extends T> reader)
	{
		readers.add(reader);
	}
	
	public List<T> read() {
		List<T> rtnValue = new ArrayList<T>();
		for(Reader<? extends T> reader: readers)
		{
			rtnValue.addAll(reader.read());
		}
		return rtnValue;
	}

	public boolean isActivated() {
		return true;
	}

}
