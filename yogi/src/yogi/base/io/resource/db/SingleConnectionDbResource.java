package yogi.base.io.resource.db;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class SingleConnectionDbResource implements DbResource {
	private DbResource dbResource;
	private Connection connection = null;
	private Connection originalConnection;
	public SingleConnectionDbResource(DbResource dbResource) {
		super();
		this.dbResource = dbResource;
	}

	public Connection getConnection() {
		if(connection == null){
			originalConnection = dbResource.getConnection();
			try {
				originalConnection.setAutoCommit(false);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			connection = new ConnectionWrapper(originalConnection);
		}
		return connection;
	}

	public String getName() {
		return dbResource.getName();
	}
	
	public void close() throws SQLException {
		if(originalConnection != null){
			originalConnection.close();
			originalConnection = null;
			connection = null;
		}
	}

	public void commit() throws SQLException {
		if(originalConnection != null)
			originalConnection.commit();
	}

	public void rollback() throws SQLException {
		if(originalConnection != null)
			originalConnection.rollback();
	}
	
}

class ConnectionWrapper implements Connection {
	public void abort(Executor arg0) throws SQLException {
		connection.abort(arg0);
	}

	public int getNetworkTimeout() throws SQLException {
		return connection.getNetworkTimeout();
	}

	public String getSchema() throws SQLException {
		return connection.getSchema();
	}

	public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException {
		connection.setNetworkTimeout(arg0, arg1);
	}

	public void setSchema(String arg0) throws SQLException {
		connection.setSchema(arg0);
	}

	private Connection connection;
	
	public ConnectionWrapper(Connection connection) {
		super();
		this.connection = connection;
	}

	public void clearWarnings() throws SQLException {
		connection.clearWarnings();
	}

	public Statement createStatement() throws SQLException {
		return connection.createStatement();
	}

	public Statement createStatement(int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return connection.createStatement(resultSetType, resultSetConcurrency,
				resultSetHoldability);
	}

	public Statement createStatement(int resultSetType, int resultSetConcurrency)
			throws SQLException {
		return connection.createStatement(resultSetType, resultSetConcurrency);
	}

	public boolean getAutoCommit() throws SQLException {
		return connection.getAutoCommit();
	}

	public String getCatalog() throws SQLException {
		return connection.getCatalog();
	}

	public int getHoldability() throws SQLException {
		return connection.getHoldability();
	}

	public DatabaseMetaData getMetaData() throws SQLException {
		return connection.getMetaData();
	}

	public int getTransactionIsolation() throws SQLException {
		return connection.getTransactionIsolation();
	}

	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return connection.getTypeMap();
	}

	public SQLWarning getWarnings() throws SQLException {
		return connection.getWarnings();
	}

	public boolean isClosed() throws SQLException {
		return connection.isClosed();
	}

	public boolean isReadOnly() throws SQLException {
		return connection.isReadOnly();
	}

	public String nativeSQL(String sql) throws SQLException {
		return connection.nativeSQL(sql);
	}

	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return connection.prepareCall(sql, resultSetType, resultSetConcurrency,
				resultSetHoldability);
	}

	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	public CallableStatement prepareCall(String sql) throws SQLException {
		return connection.prepareCall(sql);
	}

	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return connection.prepareStatement(sql, resultSetType,
				resultSetConcurrency, resultSetHoldability);
	}

	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return connection.prepareStatement(sql, resultSetType,
				resultSetConcurrency);
	}

	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
			throws SQLException {
		return connection.prepareStatement(sql, autoGeneratedKeys);
	}

	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
			throws SQLException {
		return connection.prepareStatement(sql, columnIndexes);
	}

	public PreparedStatement prepareStatement(String sql, String[] columnNames)
			throws SQLException {
		return connection.prepareStatement(sql, columnNames);
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		connection.releaseSavepoint(savepoint);
	}

	public void setCatalog(String catalog) throws SQLException {
		connection.setCatalog(catalog);
	}

	public void setHoldability(int holdability) throws SQLException {
		connection.setHoldability(holdability);
	}

	public void setReadOnly(boolean readOnly) throws SQLException {
		connection.setReadOnly(readOnly);
	}

	public Savepoint setSavepoint() throws SQLException {
		return connection.setSavepoint();
	}

	public Savepoint setSavepoint(String name) throws SQLException {
		return connection.setSavepoint(name);
	}

	public void setTransactionIsolation(int level) throws SQLException {
		connection.setTransactionIsolation(level);
	}

	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		connection.setTypeMap(map);
	}

	public void close() throws SQLException {
			
	}

	public void commit() throws SQLException {
	}

	public void rollback() throws SQLException {
		
	}

	public void rollback(Savepoint savepoint) throws SQLException {
		
	}

	public void setAutoCommit(boolean autoCommit) throws SQLException {
		
	}

	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		return connection.createArrayOf(typeName, elements);
	}

	public Blob createBlob() throws SQLException {
		return connection.createBlob();
	}

	public Clob createClob() throws SQLException {
		return connection.createClob();
	}

	public NClob createNClob() throws SQLException {
		return connection.createNClob();
	}

	public SQLXML createSQLXML() throws SQLException {
		return connection.createSQLXML();
	}

	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		return connection.createStruct(typeName, attributes);
	}

	public Properties getClientInfo() throws SQLException {
		return connection.getClientInfo();
	}

	public String getClientInfo(String name) throws SQLException {
		return connection.getClientInfo(name);
	}

	public boolean isValid(int timeout) throws SQLException {
		return connection.isValid(timeout);
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return connection.isWrapperFor(iface);
	}

	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		connection.setClientInfo(properties);
	}

	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		connection.setClientInfo(name, value);
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return connection.unwrap(iface);
	}


}
