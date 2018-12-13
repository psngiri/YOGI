package yogi.base.io;

import java.util.Collection;

import yogi.base.app.ErrorReporter;


public class SystemOutWriter<T> implements ObjectWriter<T> {
	private Formatter<T> formatter;
	private int numberOfRecordsWriten;
	
	public SystemOutWriter() {
		super();
	}

	public boolean open() {
		return true;
	}

	public boolean close() {
		ErrorReporter.get().info(String.format("Writen %1$s records", numberOfRecordsWriten));
		return true;
	}

	public boolean write(T object) {
		String value = null;
		if(formatter != null) value = formatter.format(object);
		else value = object.toString();
		if(value != null)
		{
			System.out.println(value);
			numberOfRecordsWriten++;
		}
		return true;
	}
	public void write(Collection<T> objects) {
		for (T object : objects) {
			write(object);
		}
	}

	public void setFormatter(Formatter<T> formatter) {
		this.formatter = formatter;
	}

	public void setAppend(boolean append) {
		
	}
	
	public int getNumberOfRecordsWriten() {
		return numberOfRecordsWriten;
	}


}
