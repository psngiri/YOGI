package yogi.base.io.db;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.io.resource.db.DbResource;

public class DbRecordWriterImpl implements DbRecordWriter {
	
	private static final int INVALID_COLUMN_TYPE_ERROR_CODE = 17004;
	public static int BatchSize = 10000;
	private Connection connection;
	private PreparedStatement statement;
	private boolean append = true;
	private Logger logger;
	private DbResource resource;
	private BaseDbFormatter formatter;
	private List<String> dbRecordStrings = new ArrayList<String>();
	private int count = 0;
	
	public DbRecordWriterImpl(Logger logger, DbResource resource, BaseDbFormatter formatter) {
		super();
		this.logger = logger;
		this.resource = resource;
		this.formatter = formatter;
	}

	/*
	 *  On data type miss match exception, it will construct error message to display what data type model 
	 *  is setting and what is actual data type in database, this feature is partially dependent on driver implementation
	 */
	public boolean write(DbRecord record) {
		try {
			int columnCount = record.getColumnCount();
			for(int i = 0; i < columnCount; i ++)
			{
				Object value = record.getObject(i+1);
				try {
					statement.setObject(i+1, value);
				} catch (SQLException e) {
					String errorMsg = "error in setting parameter at index " + (i + 1) + " value = " + value + " type = " + value.getClass().getName() + "for Record "+ record;
					dbRecordStrings.clear();
					if ( e.getErrorCode() == INVALID_COLUMN_TYPE_ERROR_CODE ) {
						try {
							errorMsg += " database data type is " + statement.getParameterMetaData().getParameterTypeName(i + 1); 
						} catch (SQLException e1) {
							errorMsg += " verify database datatype";
						}
					}
					throw new RuntimeException(errorMsg, e);
				}				
			}
			if(BatchSize == 0)
			{
				statement.execute();
			}else{
				statement.addBatch();
				dbRecordStrings.add(record.toString());
				count++;
				executebatch();
			}
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void executebatch() throws SQLException {
		if(count == BatchSize)
		{
			executeBatchAndLog();
			count = 0;
		}
	}

	private void executeBatchAndLog() throws SQLException {
		try {
			statement.executeBatch();
			dbRecordStrings.clear();
		} catch(BatchUpdateException e){
			int[] counts=e.getUpdateCounts();
			if (counts.length<dbRecordStrings.size()) {
				logger.log(Level.SEVERE, "Error handling record : "+ dbRecordStrings.get(counts.length));
			}else if(counts.length==dbRecordStrings.size()){
				checkUpdateCounts(counts);
				logger.log(Level.SEVERE, "Error handling records of type "+ formatter.getClass().getName()+" Check logs for details");
			}
			dbRecordStrings.clear();
			throw e;
		}
	}
	
	public void checkUpdateCounts(int[] updateCounts) {
		for (int i=0; i<updateCounts.length; i++) {
			if (updateCounts[i] == Statement.EXECUTE_FAILED) {
				logger.log(Level.WARNING, "Error handling record : "+dbRecordStrings.get(i));
			}
			if (logger.getLevel() == Level.FINE && updateCounts[i] == Statement.SUCCESS_NO_INFO) {
				logger.log(Level.FINE, "Records with Statement.SUCCESS_NO_INFO: "+dbRecordStrings.get(i));
	        }
		}
	}


	public boolean close() {
		try {
			executeBatchAndLog();
			connection.commit();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return true;
	}


	public boolean open(){
		connection = resource.getConnection();
		try {
			connection.setAutoCommit(false);
			if(!append)cleanup();
			String query = formatter.query();
			if(logger.isLoggable(Level.INFO))logger.info(query);
			statement = connection.prepareStatement(query);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	private void cleanup() throws SQLException {
		Statement statement = connection.createStatement();
		String cleanUpQuery = formatter.cleanUpQuery();
		if(cleanUpQuery == null){
			if(logger.isLoggable(Level.FINER))logger.finer(String.format("Clean up Query Not Found for " + formatter.getClass().getSimpleName()));
			return;
		}
		if(logger.isLoggable(Level.INFO))logger.info(cleanUpQuery);
		int count = statement.executeUpdate(cleanUpQuery);
		if(logger.isLoggable(Level.INFO))logger.info(String.format("Cleaned up %1$s records from %2$s query %3$s", count, resource.getName(), cleanUpQuery));
	}

	public void setAppend(boolean append) {
		this.append = append;
	}
	
}
