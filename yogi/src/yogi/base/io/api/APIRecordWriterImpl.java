package yogi.base.io.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DbRecordWriter;

public class APIRecordWriterImpl implements DbRecordWriter {
	
	private boolean append = true;
	private Logger logger;
	private APIResource resource;
	private List<DbRecord> records;
	
	public APIRecordWriterImpl(Logger logger, APIResource resource) {
		super();
		this.logger = logger;
		this.resource = resource;
	}

	public boolean write(DbRecord record) {
		return true;
	}

	public boolean close() {
		try {
			resource.writeRecords(records);
		} catch (APIException e) {
			throw new RuntimeException(e);
		}
		return true;
	}


	public boolean open(){
		records = new ArrayList<DbRecord>();
		try {
			if(!append)cleanup();
		} catch (APIException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	private void cleanup() throws APIException {
		int count = resource.cleanUp();
		if(logger.isLoggable(Level.FINE))logger.fine(String.format("Cleaned up %1$s records from %2$s", count, resource.getName()));
	}

	public void setAppend(boolean append) {
		this.append = append;
	}
	
}
