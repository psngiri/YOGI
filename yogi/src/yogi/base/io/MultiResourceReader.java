package yogi.base.io;


import yogi.base.io.resource.MultiResource;

public abstract class MultiResourceReader<T>  implements RecordReader<T> {
	private MultiResource multiResource;
	private RecordReader<T> currentReader;
	
	public MultiResourceReader(MultiResource multiResoruce) {
		this.multiResource = multiResoruce;
		currentReader = null;
	}
	
	public boolean open() {
		if(currentReader == null)
		{
			if(multiResource.hasNext())
			{
				resetCurrentReader();
			}else return false;
		}
		return currentReader.open();
	}

	protected abstract void resetCurrentReader();

	public boolean hasNext() {
		boolean rtnValue = currentReader.hasNext();
		while(!rtnValue && multiResource.hasNext())
		{
			currentReader.close();
			resetCurrentReader();
			rtnValue = currentReader.open();
			if(rtnValue)
			{
				rtnValue = currentReader.hasNext();
			}
		}
		return rtnValue;
	}

	public boolean close() {
		return currentReader.close();
	}

	public MultiResource getResource() {
		return multiResource;
	}

	public T next() {
		T record = currentReader.next();
		return record;
	}


	protected void setCurrentReader(RecordReader<T> currentReader) {
		this.currentReader = currentReader;
	}

}
