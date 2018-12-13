package yogi.base.io.resource.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import yogi.base.util.logging.Logging;

import oracle.jdbc.pool.OracleDataSource;

public class SimpleDbResource extends BaseDbResource {
	public static String JDBCUrl = "jdbc:oracle:thin:xgt/xgt@raptor.corp.amrcorp.com:1521:icapsdev";
	public static int DefaultRowPrefetchSize=10;
	protected OracleDataSource ods;
	private Logger logger = Logging.getLogger(SimpleDbResource.class);
	
	public SimpleDbResource() {
		super();
	}
	
	public SimpleDbResource(String jdbcUrl) {
		super(jdbcUrl);
	}
		
	protected void setup() {
		try {
			ods = new OracleDataSource();
			String constructJdbcURL = constructJdbcURL();
			logger.info("Creating an Oracle Database with URL:" + constructJdbcURL);
			ods.setURL(constructJdbcURL);
			ods.setConnectionProperties(getConnectionProperties());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	protected Properties getConnectionProperties() {
		Properties connectionProps = new Properties();
		connectionProps.setProperty("defaultRowPrefetch",String.valueOf(DefaultRowPrefetchSize));//Sets Prefetch Row Size 
		return connectionProps;
	}


	protected Connection getDbConnection() {
		try {
			if (ods == null)setup();
			return ods.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected String getJDBCURL() {
		return JDBCUrl;
	}
	
}
