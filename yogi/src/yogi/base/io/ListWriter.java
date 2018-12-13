package yogi.base.io;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import yogi.base.Filter;
import yogi.base.Selector;

public abstract class ListWriter<T> implements Writer{	
	private ObjectWriter<T> writer;
	private Selector<? super T> selector;
	private Comparator<T> comparator;
	
	protected ListWriter(ObjectWriter<T> writer, Selector<? super T> selector) {
		super();		
		this.writer = writer;
		this.selector = selector;
	}
	
	public Selector<? super T> getSelector() {
		return selector;
	}

	public ObjectWriter<T> getWriter() {
		if(writer == null) throw new RuntimeException("No Writer Set, please set the Writer.");
		return writer;
	}
	
	public void setSelector(Selector<? super T> selector) {
		this.selector = selector;
	}

	public void setWriter(ObjectWriter<T> writer) {
		this.writer = writer;
	}

	protected Comparator<T> getComparator() {
		return comparator;
	}

	protected void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	protected abstract List<T> getList();
	
	public void write()
	{
		ObjectWriter<T> myWriter = getWriter();
		if(!myWriter.open()) return;
		List<T> list = getList();
		if(selector != null)
			list = Filter.filter(list,selector);
		if(comparator != null)
			Collections.sort(list,comparator);
		writer.write(list);
		myWriter.close();
	}
		
	public boolean isActivated() {
		return true;
	}
}
