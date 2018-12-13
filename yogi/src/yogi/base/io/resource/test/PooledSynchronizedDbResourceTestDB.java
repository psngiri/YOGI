package yogi.base.io.resource.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.TestCase;

import yogi.base.io.resource.db.PooledSynchronizedDbResource;
import yogi.base.io.resource.db.SimpleDbResource;

public class PooledSynchronizedDbResourceTestDB extends TestCase {

	SimpleDbResource resource;
	Connection connection;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		SimpleDbResource.DefaultRowPrefetchSize=100;
		SimpleDbResource.JDBCUrl = "jdbc:oracle:thin:weblogic/weblogic@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=rmdeva.tul.aa.com)(PORT=1521))(CONNECT_DATA=(SERVER=dedicated)(SERVICE_NAME=ngpd.tul.aa.com)))";
		resource = new PooledSynchronizedDbResource();
		connection = resource.getConnection();
	}

	@Override
	protected void tearDown() throws Exception {
		connection.close();
		super.tearDown();
	}

	public void test() throws SQLException
	{
		Statement createStatement = connection.createStatement();
		ResultSet resultSet = createStatement.executeQuery("select USER from dual");
		if(resultSet.next())
		{
			System.out.println(resultSet.getObject(1));			
		}
		System.out.println(createStatement.getFetchSize());
	}
}