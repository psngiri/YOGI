package yogi.base.io;

import java.util.ArrayList;
import java.util.List;

public class MultiFactoryWriter<T> implements Writer {
	private List<FactoryWriter<? extends T>> factoryWriters = new ArrayList<FactoryWriter<? extends T>>();
	private boolean append;
	
	public MultiFactoryWriter() {
		super();
		append = false;
	}

	public void addFactoryWriter(FactoryWriter<? extends T> factoryWriter)
	{
		factoryWriters.add(factoryWriter);
	}
	
	public void write() {
		for(FactoryWriter<? extends T> factoryWriter: factoryWriters)
		{
			factoryWriter.getWriter().setAppend(append);
			factoryWriter.write();
			if(append == false) append = true;
		}
	}

	public boolean isAppend() {
		return append;
	}

	public void setAppend(boolean append) {
		this.append = append;
	}

	public boolean isActivated() {
		return true;
	}

}
