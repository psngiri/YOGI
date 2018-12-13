package yogi.base.io.resource.db.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import yogi.base.io.resource.db.BaseDbResource;

public class HiveDbResource extends BaseDbResource {
	public static String JDBCUrl = "jdbc:hive2://bdhcld01.hdq.aa.com:10000";
	public static String JDBCDriver = "com.cloudera.hive.jdbc4.HS2Driver";
	private static boolean driverLoaded;
	
	public HiveDbResource() {
		super();
	}
	
	public HiveDbResource(String jdbcUrl) {
		super(jdbcUrl);
	}
		
	protected void setup() {
		try {
			Class.forName(JDBCDriver);
			driverLoaded = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected Connection getDbConnection() {
		try {
			if (!driverLoaded)setup();
			return DriverManager.getConnection(constructJdbcURL());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected String getJDBCURL() {
		return JDBCUrl;
	}
	
}
