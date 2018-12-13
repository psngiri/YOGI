package yogi.base.io.resource.db;

import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Vikram Vadavala
 *
 */
public class PooledSynchronizedDbResource extends SynchronizedDbResource {
	
	public static int maxConnections=100;
	
	public PooledSynchronizedDbResource() {
		super();
	}
	
	public PooledSynchronizedDbResource(String jdbcUrl) {
		super(jdbcUrl);
	}
			
	@Override
	protected void setup() {
		super.setup();
		try {
			ods.setConnectionCacheName("ImplicitCache01"); //cache's name
			ods.setImplicitCachingEnabled(true);// Enabling Connection Caching
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected Properties getConnectionProperties() {
		Properties connectionProps = super.getConnectionProperties();
		connectionProps.setProperty("MinLimit", "5");//Sets limit on Minimum number of connections. Defaults to 0
		connectionProps.setProperty("MaxLimit", String.valueOf(maxConnections));//Sets limit on Maximum number of connections. No default
		connectionProps.setProperty("InitialLimit", "10");//Sets limit on the number of initial connections populated, when the cache is first created. Defaults to 0.
		connectionProps.setProperty("InactivityTimeout", "900");//Sets the Maximum time, in seconds, a connection in the cache can remain idle (that is not checked out of the cache). Defaults to 0.
		connectionProps.setProperty("AbandonedConnectionTimeout", "900");//Sets the maximum time a checked out connection can remain unused (no SQL activity) before the connection is closed and returned to the cache. Defaults to 0.
		connectionProps.setProperty("MaxStatementsLimit  ", "10");//Sets the Maximum number of statements that each connection keeps open, for statement caching. Defaults to 0. 
		connectionProps.setProperty("PropertyCheckInterval", "300"); // time interval at which the cache manager inspects and enforces all specified cache properties
		return connectionProps;
	}
	
	

}
