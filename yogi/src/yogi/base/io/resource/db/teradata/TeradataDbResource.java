package yogi.base.io.resource.db.teradata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import yogi.base.io.resource.db.DbResource;
import yogi.base.util.logging.Logging;
import yogi.property.PropertyReplacer;

public class TeradataDbResource implements DbResource {
	public static String teraJdbcUrl="jdbc:teradata://edtdpcop2.corpaa.aa.com";       // for prod
	public static String accountName="" ;
	public static String passd=""; 
	
	private Logger logger = Logging.getLogger(TeradataDbResource.class);
	public String jdbcUrl;
	
	public TeradataDbResource(String jdbcUrl) {
		super();
		this.jdbcUrl = jdbcUrl;
	}
	
	public TeradataDbResource() {
		super();
	}
	
	private String constructJdbcURL() {
		if (jdbcUrl == null) jdbcUrl = teraJdbcUrl;
		jdbcUrl = new PropertyReplacer().replaceVariables(jdbcUrl);
		return jdbcUrl;
	}

	private String getJdbcURL() {
		if(jdbcUrl == null) constructJdbcURL();
		return jdbcUrl;
	}

	public Connection getConnection() {
		try {
			String constructJdbcURL = constructJdbcURL();
			logger.info("Creating a Teradata Database with URL:" + constructJdbcURL);
			return DriverManager.getConnection(constructJdbcURL, accountName, passd);
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public String getName() {
		int lastIndexOf = getJdbcURL().lastIndexOf(':');
		return getJdbcURL().substring(lastIndexOf);
	}

}
