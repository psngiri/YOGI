package yogi.base.io;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.io.resource.ResourceAssistant;
import yogi.base.io.resource.SystemResource;
import yogi.base.util.logging.Logging;

public class FileRecordReader extends BaseFileReader implements RecordReader<String> {
	private static Logger logger = Logging.getLogger(FileRecordReader.class);
	private String record;
	
	public FileRecordReader(String fileName) {
		this(fileName, 0);
	}
	
	public FileRecordReader(String fileName, int headerLineCount) {
		this(ResourceAssistant.getResource(fileName), headerLineCount);
	}
	
	public FileRecordReader(SystemResource resource, int headerLineCount) {
		super(resource, headerLineCount);
	}
	
	public FileRecordReader(SystemResource resource) {
		this(resource, 0);
	}
	    
	public boolean hasNext() {
		if(record != null) return true;
		try {
			if((record = reader.readLine()) != null) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String next() {
		if(record == null) throw new RuntimeException("Call hasNext() and check if a record exists before calling next(). Null record");
		if(logger.isLoggable(Level.FINEST))logger.finest("Processing Record:" + record);
		String rtnValue = record;
		record = null;
		return rtnValue;
	}

}
