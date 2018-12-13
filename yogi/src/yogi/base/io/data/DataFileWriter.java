package yogi.base.io.data;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.Util;
import yogi.base.io.ObjectWriter;
import yogi.base.io.resource.FileResource;
import yogi.base.util.logging.Logging;

public class DataFileWriter<T> implements ObjectWriter<T> {
	private int numberOfRecordsWriten;
	private static Logger logger = Logging.getLogger(DataFileWriter.class);
	private FileResource fileResource;
	protected DataOutputStream writer;
	private boolean append = false;
	private DataFormatter<T> formatter;

	public DataFileWriter(String fileName, DataFormatter<T> formatter) {
		super();
		this.fileResource = new FileResource(fileName);
		this.formatter = formatter;
	}
	
	public DataFileWriter(File file,DataFormatter<T> formatter) {
		super();
		this.fileResource = new FileResource(file);
		this.formatter = formatter;
	}

	public File getFile() {
		return fileResource.getFile();
	}

	public boolean close() {
		try {
			logger.info(String.format("Writen %1$s records to File:%2$s", numberOfRecordsWriten, getFile().getAbsolutePath()));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean isAppend() {
		return append;
	}

	public void setAppend(boolean append) {
		this.append = append;
	}

	public boolean open() {
		Util.checkAndCreateFileAlongWithParentDirsAsRequired(getFile());
		try {
			writer = new DataOutputStream(new BufferedOutputStream(new java.io.FileOutputStream(
					getFile(), isAppend())));
			 if(logger.isLoggable(Level.INFO))
			 {
				 logger.info("Writing to file " + getFile().getAbsolutePath());
			 }
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	@Override
	public boolean write(T object) {
		if(formatter.format(object, writer)) 
		{
			numberOfRecordsWriten++;
		}
		return true;
	}

	@Override
	public void write(Collection<T> objects) {
		for (T object : objects) {
			write(object);
		}
	}

	@Override
	public int getNumberOfRecordsWriten() {
		return numberOfRecordsWriten;
	}


}
