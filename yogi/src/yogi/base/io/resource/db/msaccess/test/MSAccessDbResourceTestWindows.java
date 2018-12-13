package yogi.base.io.resource.db.msaccess.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import junit.framework.TestCase;

import yogi.base.io.resource.db.msaccess.MSAccessDbResource;

public class MSAccessDbResourceTestWindows extends TestCase {

	public void test1()
	{
		MSAccessDbResource dbResource = MSAccessDbResource.getDbResource("data/exampleTestData/MasterSchedule.mdb");
		Connection connection = dbResource.getConnection();
		System.out.println("Connection Successful ");
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement("select * from MasterSchedule");
			ResultSet rst = pstmt.executeQuery();
			if (rst.next())
			{
				System.out.println(rst.getString(1));
			}
			ResultSetMetaData metaData = rst.getMetaData();
			System.out.println(metaData.getColumnCount());
			for(int i = 0; i < metaData.getColumnCount(); i ++)
			{
				System.out.print(metaData.getColumnName(i+1) + ", ");
			}
			rst.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
