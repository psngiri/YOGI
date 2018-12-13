package yogi.base.io.resource.db.msaccess;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import yogi.base.io.resource.db.DbResource;

public class MSAccessDbResource implements DbResource {
	public static String JDBCUrl = "jdbc:oracle:thin:giri/giri@wizard.corp.amrcorp.com:1521:icapsdev";
	public String jdbcUrl;
	private String mdbFileName;
	
	public static MSAccessDbResource getDbResource(String dbfileName) {
		File file = new File(dbfileName);
		
		String absolutePath = file.getAbsolutePath().replace("\\", "/");
		MSAccessDbResource fromDbResource = new MSAccessDbResource(absolutePath);
		return fromDbResource;
	}
	
	public MSAccessDbResource(String mdbFileName) {
		super();
		this.mdbFileName = mdbFileName;
		setup();
	}


	private void setup() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		jdbcUrl = String.format("jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ=%1$s;", mdbFileName);
	}
	

	public Connection getConnection() {
		try {
			return DriverManager.getConnection(jdbcUrl);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public String getName() {
		return mdbFileName;
	}

}
