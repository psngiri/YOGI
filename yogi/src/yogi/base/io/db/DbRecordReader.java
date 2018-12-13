package yogi.base.io.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.io.RecordReader;
import yogi.base.io.resource.Resource;
import yogi.base.io.resource.db.DbResource;
import yogi.base.util.logging.Logging;

public class DbRecordReader implements RecordReader<DbRecord> {
	private static Logger logger = Logging.getLogger(DbRecordReader.class);
	public static int FetchSize = 0;
	private ResultSet resultSet;
	private DbRecord record;
	private DbResource resource;
	private Statement statement;
	private Connection connection;
	private int numberOfColumns = 0;
	private String query;
	

	public DbRecordReader(DbResource resource) {
		super();
		this.resource = resource;
	}

	public boolean hasNext() {
		try {
			if(getResultSet().next()) {
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	public DbRecord next() {
		try {
			DbRecord myRecord = getRecord();
			logDbRecord(myRecord);
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
			for(int i = 1; i <= numberOfColumns; i ++)
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

	private DbRecord getRecord() {
		if(record == null) record = new DbRecordImpl(getResultSet());
		return record;
	}

	public boolean close() {
		try {
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return true;
	}

	ResultSet getResultSet() {
		return resultSet;
	}

	public boolean open(){
		connection = resource.getConnection();
		try {
			statement = connection.createStatement();
			statement.setFetchSize(FetchSize);
			String query = getQuery();
			logger.info(query);
			resultSet = statement.executeQuery(query);
			numberOfColumns = resultSet.getMetaData().getColumnCount();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	public Resource getResource() {
		return resource;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}
