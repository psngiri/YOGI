package yogi.csi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import yogi.base.app.ApplicationProperties;
import yogi.base.app.ErrorReporter;
import yogi.base.io.resource.db.DbResource;

public class DbCSIApplicationStatusUpdater extends BaseCSIApplicationStatusUpdater
{
	private DbResource dbResource;

	public DbCSIApplicationStatusUpdater(DbResource dbResource) {
		super();
		this.dbResource = dbResource;
	}
	
	public void updateStatus(String status)
	{
		String taskID = ApplicationProperties.TaskID;
		Connection connection = dbResource.getConnection();
		String query = "update actvty set status = '"+ status + "' where actvty_id = " + taskID ;
		
		try {
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(query);
			int count = pstmt.executeUpdate();
			if(count != 1) ErrorReporter.get().error("Failed to update Applicaiton status in CSI. TaskID = " + taskID + " status = " + status);
			pstmt.close();
		} catch (SQLException e) {
			Object message = "Error in updateing Applicaiton status in CSI. TaskID = " + taskID + " status = " + status;
			ErrorReporter.get().error(message, e);
		}finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				Object message = "Error in updateing Applicaiton status in CSI. TaskID = " + taskID + " status = " + status;
				ErrorReporter.get().error(message, e);
			}
		}	
	}
	
}