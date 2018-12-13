package yogi.base.io.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.io.resource.db.DbResource;
import yogi.base.util.logging.Logging;

/*
 * This class is to centralize database operations. 
 */

public class QueryExecutor {
	
	private static QueryExecutor queryExcecutor;
	private Logger logger = Logging.getLogger(QueryExecutor.class);
	
	public static QueryExecutor get() {
		if ( queryExcecutor == null ) queryExcecutor = new QueryExecutor();
		return queryExcecutor;
	}
	
	private static int JDBCDefaultFetchSize = 10;
	
	private static int JDBCDefaultBatchSize = 10;
	
	public List<Object[]> executeQuery(DbResource resource, String query) throws DBException {
		return executeQuery(resource, query, JDBCDefaultFetchSize);
	}
	
	public List<Object[]> executeQuery(DbResource resource, String query, Object... parameters) throws DBException {
		return executeQuery(resource, query, parameters, JDBCDefaultFetchSize);
	}
		
	public List<Object[]> executeQuery(DbResource resource, String query, int fetchSize) throws DBException {
		Connection connection = resource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setFetchSize(fetchSize);
			if(logger.isLoggable(Level.INFO)) logger.info("Executing Query : " + query);
			rst = pstmt.executeQuery();
			return readResults(query, rst);
		} catch (SQLException e) {
			throw DbExceptionHandler.createDbException(query, e);
		}
		finally {
			close(connection, pstmt, rst );
		}
	}
	
	public List<Object[]> executeQuery(DbResource resource, String query, Object[] parameters, int fetchSize) throws DBException {
		Connection connection = resource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = connection.prepareStatement(query);
			setParameters(query, pstmt, parameters);
			pstmt.setFetchSize(fetchSize);
			if(logger.isLoggable(Level.INFO)) logger.info("Executing Query : " + query+" with parameters: "+getParameterString(parameters));
			rst = pstmt.executeQuery();
			return readResults(query, rst);
		} catch (SQLException e) {
			throw DbExceptionHandler.createDbException(query, pstmt, e, parameters);
		}
		finally {
			close(connection, pstmt, rst );
		}
	}
	
	private List<Object[]> readResults(String query, ResultSet rst) throws DBException {
		List<Object[]> list = new ArrayList<Object[]>();
		int numberOfColumns=-1;
		try {
			numberOfColumns = rst.getMetaData().getColumnCount();
			while ( rst.next() ) {
				Object[] row = new Object[numberOfColumns];
				for(int i = 0; i < numberOfColumns; i++) {
					row[i] = rst.getObject(i + 1);
				}
				list.add(row);
			}
			return list;
		} catch (SQLException e) {
			throw DbExceptionHandler.createDbException(query, rst, e, numberOfColumns); 
		}
	}
	
	public int executeUpdate(DbResource resource, String query) throws DBException {
		Connection connection = resource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = connection.prepareStatement(query);
			if(logger.isLoggable(Level.INFO)) logger.info("Executing DML Query : " + query);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw DbExceptionHandler.createDbException(query, e);
		}
		finally {
			close(connection, pstmt, rst );
		}
	}
	
	public int executeUpdate(DbResource resource, String query, Object... parameters) throws DBException {
		Connection connection = resource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = connection.prepareStatement(query);
			setParameters(query, pstmt, parameters);
			if(logger.isLoggable(Level.INFO)) logger.info("Executing DML Query : " + query+" with parameters: "+getParameterString(parameters));
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw DbExceptionHandler.createDbException(query, pstmt, e , parameters);
		}
		finally {
			close(connection, pstmt, rst );
		}
	}
	
	public int[] executeBatchUpdate(DbResource resource, String query, List<Object[]> batchPrameters) throws DBException {
		return executeBatchUpdate(resource, query, batchPrameters, JDBCDefaultBatchSize);
	}
	
	public int[] executeBatchUpdate(DbResource resource, String query, List<Object[]> batchPrameters, int commitCount) throws DBException {
		int[] allCounts = new int[batchPrameters.size()];
		if(logger.isLoggable(Level.INFO)) logger.info("Executing Batch Update Query : " + query);
		List<List<Object[]>> batchList = getBatchSizeParametersLists(batchPrameters, commitCount);
		int i = 0;
		for (List<Object[]> list : batchList) {
			int[] counts = executeJDBCBatchUpdate(resource, query, list);
			for (int j = 0; j < counts.length; j++) {
				allCounts[i++] = counts[j];
			}
		}
		return allCounts;
	}
	
	private int[] executeJDBCBatchUpdate(DbResource resource, String query, List<Object[]> batchPrameters) throws DBException {
		Connection connection = resource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(query);
			setBatchParameters(query, pstmt, batchPrameters);
			int[] count = pstmt.executeBatch();
			connection.commit();
			return count;
		} catch (SQLException e) {
			throw DbExceptionHandler.createDbException(query, e);
		}
		finally {
			close(connection, pstmt, rst );
		}
	}
	
	private void setBatchParameters(String query, PreparedStatement pstmt, List<Object[]> batchPrameters) throws DBException {
		for (Object[] parameters : batchPrameters) {
			setParameters(query, pstmt, parameters);
			try {
				pstmt.addBatch();
			} catch (SQLException e) {
				throw DbExceptionHandler.createDbException(query, pstmt, e, parameters);
			}
		}
	}
	
	private void setParameters(String query, PreparedStatement pstmt, Object[] parameters) throws DBException {
		for(int i = 0; i < parameters.length; i++) {
			try {
				pstmt.setObject(i+1, parameters[i]);
			} catch (SQLException e) {
				throw DbExceptionHandler.createDbException(query, pstmt, e, i+1, parameters[i]);
			}				
		}
	}
	
	private List<List<Object[]>> getBatchSizeParametersLists(List<Object[]> batchPrameters, int commitCount) {
		List<List<Object[]>> batchList = new ArrayList<List<Object[]>>();
		int batchSize = batchPrameters.size();
		int totalBatches = batchSize / commitCount ;
		int remainder = batchSize % commitCount;
		for (int i = 0; i < totalBatches; i++ ) {
			batchList.add(batchPrameters.subList(i * commitCount, (i + 1 )* commitCount));
		}
		batchList.add(batchPrameters.subList(totalBatches * commitCount, totalBatches * commitCount + remainder));
		return batchList;
	}
	
	private void close(Connection connection, PreparedStatement pstmt, ResultSet rst) {
		if ( rst != null ) {
			try {
				rst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if ( pstmt != null ) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if ( connection != null ) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean executeStoredProcedure(DbResource resource, String storedProcedure) throws DBException {
		Connection connection = resource.getConnection();
		CallableStatement prepareCall = null;
		try {
			prepareCall = connection.prepareCall(storedProcedure);
			if(logger.isLoggable(Level.INFO)) logger.info("Executing Stored Procedure : " + storedProcedure);
			return prepareCall.execute();
		} catch (SQLException e) {
			throw DbExceptionHandler.createDbException(storedProcedure, e);
		}
		finally {
			close(connection, prepareCall, null );
		}
	}
	
	private String getParameterString(Object[] parameters){
		StringBuffer parameterStringBuffer = new StringBuffer();
		for (int i = 0; i < parameters.length; i++) {
			parameterStringBuffer.append(parameters[i]);
			if (i!=(parameters.length-1)) {
				parameterStringBuffer.append(",");
			}
		}
		parameterStringBuffer.trimToSize();
		return parameterStringBuffer.toString();
	}

}
