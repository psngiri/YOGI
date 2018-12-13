package yogi.base.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import yogi.base.Manager;
import yogi.base.Selector;

public class MultiManagersWriter<T> implements Writer{
	private List <Manager<? extends T>> managers;
	private ObjectWriter<T> writer;
	private Selector<? super T> selector;
	private Comparator<T> comparator;
	
	protected MultiManagersWriter(List <Manager<? extends T>> managers, ObjectWriter<T> writer, Selector<? super T> selector) {
		super();
		this.managers = managers;
		this.writer = writer;
		this.selector = selector;
	}
	protected MultiManagersWriter(ObjectWriter<T> writer, Selector<? super T> selector, Manager<? extends T>... managers) {
		super();
		List <Manager<? extends T>> tempManagerList = new ArrayList<Manager<? extends T>>();
		tempManagerList.addAll(Arrays.asList(managers));
		this.managers = tempManagerList;
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

	public List<Manager<? extends T>> getManagers() {
		return managers;
	}
	public void setManagers(List<Manager<? extends T>> managers) {
		this.managers = managers;
	}
	public void write()
	{
		ObjectWriter<T> myWriter = getWriter();
		if(!myWriter.open()) return;		
		List <T> output = new ArrayList<T>();	
		for(Manager<? extends T> manager : managers){
			output.addAll(manager.find(selector));
		}
		if(comparator != null)			
			Collections.sort(output,comparator);
		
		myWriter.write(output);
		myWriter.close();
	}

	public boolean isActivated() {
		return true;
	}
}
