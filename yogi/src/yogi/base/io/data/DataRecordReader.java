package yogi.base.io.data;

import java.io.BufferedInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.io.RecordReader;
import yogi.base.io.resource.ResourceAssistant;
import yogi.base.io.resource.SystemResource;
import yogi.base.util.logging.Logging;

public class DataRecordReader implements RecordReader<DataInput> {

	private static Logger logger = Logging.getLogger(DataRecordReader.class);
	private SystemResource resource;
	protected DataInputStream reader;

	public DataRecordReader(String fileName)
	{
		this(ResourceAssistant.getResource(fileName));
	}
	
	public DataRecordReader(SystemResource resource)
	{
		super();
		this.resource = resource;
	}

	public SystemResource getResource() {
		return resource;
	}

	public boolean open(){
		if (!resource.canRead()) {
			throw new RuntimeException("Could not read from " + resource);
		}
		 reader = new DataInputStream(new BufferedInputStream(resource.getInputStream()));
		 if(logger.isLoggable(Level.INFO))
		 {
			 logger.info("Reading from " + resource);
		 }
		return true;
	}
	
	public boolean close() {
		try {
			reader.close();
		} catch (IOException e) {
			throw new RuntimeException("Error in " + resource, e);
		}
		return true;
	}

	@Override
	public boolean hasNext() {
		try {
			int available = reader.available();
			if(available <= 0) return false;
			else return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public DataInput next() {
		return reader;
	}

	
}
