package yogi.base.io;

import java.util.Comparator;

import yogi.base.Factory;
import yogi.base.Selector;
import yogi.base.io.db.dump.DumpProperties;

public class FactoryWriter<T> implements Writer{
	public static boolean logDataToDump = false;
	private Factory<T> factory;
	private ObjectWriter<T> writer;
	private Selector<? super T> selector;
	private Comparator<T> comparator;
	
	protected FactoryWriter(Factory<T> factory, ObjectWriter<T> writer, Selector<? super T> selector) {
		super();
		this.factory = factory;
		this.writer = writer;
		this.selector = selector;
	}
	
	public Factory<T> getFactory() {
		return factory;
	}

	public Selector<? super T> getSelector() {
		return selector;
	}

	public ObjectWriter<T> getWriter() {
		if(writer == null) throw new RuntimeException("No Writer Set, please set the Writer.");
		return writer;
	}

	public void setFactory(Factory<T> factory) {
		this.factory = factory;
	}

	public void setSelector(Selector<? super T> selector) {
		this.selector = selector;
	}

	public void setWriter(ObjectWriter<T> writer) {
		this.writer = writer;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	protected void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public void write()
	{
		ObjectWriter<T> myWriter = getWriter();
		if(!myWriter.open()) return;
		try {
			factory.write(myWriter, selector, comparator);
			myWriter.close();
		} catch (Exception e) {
			dumpDataToFile(myWriter);
			throw new RuntimeException(e); 
		}
	}

	private void dumpDataToFile(ObjectWriter<T> myWriter) {
		if(!logDataToDump) return;
		DumpProperties.LoadToDb = false;
		if(!myWriter.open()) return;
		factory.write(myWriter, selector, comparator);
		myWriter.close();	
		DumpProperties.LoadToDb = true;
	}

	public boolean isActivated() {
		return true;
	}
}
