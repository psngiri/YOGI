package yogi.base.io;

import java.io.File;
import java.util.Collection;
import java.util.logging.Logger;

import yogi.base.util.logging.Logging;


public class FileWriter<T> extends BaseHeaderFileWriter implements yogi.base.io.ObjectWriter<T> {
	private static Logger logger = Logging.getLogger(FileWriter.class);
	private Formatter<T> formatter = new FormatterAdapter<T>();
	private int numberOfRecordsWriten;
	
	public FileWriter(File file) {
		super(file);
	}

	public FileWriter(File file, Formatter<T> formatter, boolean append) {
		super(file);
		setFormatter(formatter);
		setAppend(append);
	}
	
	public FileWriter(String fileName) {
		super(fileName);
	}

	public FileWriter(String fileName, Formatter<T> formatter) {
		super(fileName);
		setFormatter(formatter);
	}	
	
	protected void setFormatter(Formatter<T> formatter) {
		this.formatter = formatter;
	}

	protected Formatter<T> getFormatter() {
		return formatter;
	}

	@SuppressWarnings("unchecked")
	public FileWriter(String fileName, String formatterClassName) {
		super(fileName);
		Class<?> formatterClass;
		try {
			formatterClass = Class.forName(formatterClassName);
			setFormatter((Formatter<T>) formatterClass.newInstance());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public int getNumberOfRecordsWriten() {
		return numberOfRecordsWriten;
	}

	public void write(Collection<T> objects) {
		for (T object : objects) {
			write(object);
		}
	}

	public boolean write(T object) {
		String format = getFormatter().format(object);
		if(format != null) 
		{
			writer.println(format);
			numberOfRecordsWriten++;
		}
		return true;
	}

	@Override
	public boolean close() {
		boolean rtnValue = super.close();
		if(rtnValue)
		{
			logger.info(String.format("Writen %1$s records to File:%2$s", numberOfRecordsWriten, getFile().getAbsolutePath()));
		}
		return rtnValue;
	}

}
