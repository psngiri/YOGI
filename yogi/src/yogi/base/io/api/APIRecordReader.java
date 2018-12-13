package yogi.base.io.api;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.io.RecordReader;
import yogi.base.io.db.DbRecord;
import yogi.base.io.resource.Resource;
import yogi.base.util.logging.Logging;

public class APIRecordReader implements RecordReader<DbRecord> {
	private static Logger logger = Logging.getLogger(APIRecordReader.class);
	private DbRecord record;
	private APIResource resource;
	private List<DbRecord> records = null;
	private int currentIndex = 0;
	

	public APIRecordReader(APIResource resource) {
		super();
		this.resource = resource;
	}

	public boolean hasNext() {
		return currentIndex < records.size();
	}

	public DbRecord next() {
		try {
			DbRecord myRecord = records.get(currentIndex);
			logDbRecord(myRecord);
			currentIndex ++;
			return myRecord;
		} catch (RuntimeException e) {
			logger.severe("Error in scanning record: " + record);
			throw e;
		}
	}

	protected void logDbRecord(DbRecord myRecord) {
		if(!logger.isLoggable(Level.FINEST)) return;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Processing Record:");
		try {
			for(int i = 1; i <= myRecord.getColumnCount(); i ++)
			{
					stringBuilder.append(myRecord.getString(i)).append(' ');
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally
		{
			logger.finest(stringBuilder.toString());
		}
	}

	public boolean close() {
		records = null;
		return true;
	}

	public boolean open(){
		try {
			records = resource.getRecords();
		} catch (APIException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	public Resource getResource() {
		return resource;
	}

}
