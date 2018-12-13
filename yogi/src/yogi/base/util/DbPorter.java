package yogi.base.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import yogi.base.io.resource.db.DbResource;

public class DbPorter {

	public static void port(DbResource fromDb, String fromQuery, DbResource toDb, String toQuery, int batchSize)
	{
		Connection fromConnection = fromDb.getConnection();
		Connection toConnection = toDb.getConnection();
		try {
			PreparedStatement fromPstmt = fromConnection.prepareStatement(fromQuery);
			PreparedStatement toPstmt = toConnection.prepareStatement(toQuery);
			ResultSet rst = fromPstmt.executeQuery();
			ResultSetMetaData metaData = rst.getMetaData();
			for(int i = 0; i < metaData.getColumnCount(); i ++)
			{
				int columnIndex = i+1;
				System.out.print( metaData.getColumnName(columnIndex) + ";");
			}
			System.out.println();
			int batchCount = 0;
			while (rst.next())
			{
				for(int i = 0; i < metaData.getColumnCount(); i ++)
				{
					int columnIndex = i+1;
					Object value = rst.getObject(columnIndex);
					toPstmt.setObject(columnIndex, value);
					System.out.print( value + ";");
				}
				toPstmt.addBatch();
				System.out.println();
				batchCount ++;
				if(batchSize != 0)
				{
					if(batchCount == batchSize)
					{
						executeBatch(toPstmt);
						batchCount = 0;
					}
				}else
				{
					executeUpdate(toPstmt);
				}
			}
			if(batchSize != 0)
			{
				executeBatch(toPstmt);
			}
			rst.close();
			fromPstmt.close();
			toPstmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally
		{
			try {
				fromConnection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static void executeUpdate(PreparedStatement toPstmt) throws SQLException {
		toPstmt.executeUpdate();
	}

	private static void executeBatch(PreparedStatement toPstmt) throws SQLException {
		toPstmt.executeBatch();
	}
}
