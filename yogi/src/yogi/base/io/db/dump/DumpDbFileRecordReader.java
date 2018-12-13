package yogi.base.io.db.dump;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.app.ErrorReporter;
import yogi.base.io.BaseFileReader;
import yogi.base.io.RecordReader;
import yogi.base.io.db.DbRecord;
import yogi.base.io.resource.ResourceAssistant;
import yogi.base.io.resource.SystemResource;
import yogi.base.util.logging.Logging;

public class DumpDbFileRecordReader extends BaseFileReader implements RecordReader<DbRecord> {
	private static Logger logger = Logging.getLogger(DumpDbFileRecordReader.class);
	private String record;
	
	public DumpDbFileRecordReader(String fileName) {
		this(fileName, 0);
	}
	
	public DumpDbFileRecordReader(String fileName, int headerLineCount) {
		this(ResourceAssistant.getResource(fileName), headerLineCount);
	}
	
	public DumpDbFileRecordReader(SystemResource resource, int headerLineCount) {
		super(resource, headerLineCount);
	}
	
	public DumpDbFileRecordReader(SystemResource resource) {
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

	public DbRecord next() {
		if(record == null) throw new RuntimeException("Call hasNext() and check if a record exists before calling next(). Null record");
		if(logger.isLoggable(Level.FINEST))logger.finest("Processing Record:" + record);
		String rtnValue = record;
		record = null;
		return new DumpDbRecord(rtnValue);
	}

	@Override
	public boolean open() {
		try {
			return super.open();
		} catch (RuntimeException e) {
			ErrorReporter.get().warning(e.getMessage());
		}
		return false;
	}

	public static List<DbRecord> getDbRecords(String fileName) {
		List<DbRecord> rtnValue = new ArrayList<DbRecord>();
		DumpDbFileRecordReader  reader = new DumpDbFileRecordReader(fileName);
		reader.open();
		while(reader.hasNext())
		{
			rtnValue.add(reader.next());
		}
		reader.close();
		return rtnValue;
	}

}
