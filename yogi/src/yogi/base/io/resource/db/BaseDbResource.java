package yogi.base.io.resource.db;

import java.sql.Connection;

import yogi.base.app.ErrorReporter;
import yogi.property.PropertyReplacer;

public abstract class BaseDbResource implements DbResource {
	public String jdbcUrl;
	public static int Retry = 4;
	public static int RetrySleep = 2000;
	
	public BaseDbResource() {
		super();
	}
	
	public BaseDbResource(String jdbcUrl) {
		this();
		this.jdbcUrl = jdbcUrl;
	}
		
	protected abstract void setup();


	protected String constructJdbcURL() {
		if (jdbcUrl == null) jdbcUrl = getJDBCURL();
		jdbcUrl = new PropertyReplacer().replaceVariables(jdbcUrl);
		return jdbcUrl;
	}

	protected abstract String getJDBCURL();

	private String getJdbcURL() {
		if(jdbcUrl == null) constructJdbcURL();
		return jdbcUrl;
	}

	protected abstract Connection getDbConnection();
	
	@Override
	public Connection getConnection()  {
		if(Retry==0) return getDbConnection();
		Connection connection=null;
		int i = 0;
		Exception error = null;
		while(Retry != i)
		{
			i++;
			try {
				connection = getDbConnection();
				error = null;
				break;
			} catch (Exception e) {
				error = e;
				ErrorReporter.get().warning("Could not get Database Connection for Database "+getName()+", Retyring "+i);
  			}
			try {
				Thread.sleep(RetrySleep);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		if(error != null) throw new RuntimeException(error);
		return connection;
	}

	public String getName() {
		int lastIndexOf = getJdbcURL().lastIndexOf(':');
		if(lastIndexOf < 0) return "";
		return getJdbcURL().substring(lastIndexOf);
	}
	
}
