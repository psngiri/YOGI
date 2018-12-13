package yogi.property.parameter.io.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.resource.db.DbResource;
import yogi.property.Property;

public class CSIParameterTableReader extends  DefaultDbRecordReader<Property> {
	private  DbResource csiResource;
	private  String paramTable;
	private String version;
	public static boolean RUN = true;
	private CSIParameterReader parameterReader;
	
	public CSIParameterTableReader(DbResource resource, String parmTable, String version_id, CSIParameterReader parameterReader) {
		super(resource);
		this.csiResource = resource;
		this.paramTable = parmTable;
		this.version = version_id;		
		this.parameterReader = parameterReader;
	}
	
	@Override
	public List<Property> read()
	{		
		Connection connection = csiResource.getConnection();
		String parameterName = null;
		String parameterValue = null;
		String dateString = null;
		String query = "SELECT * FROM "+ paramTable + " WHERE VRSN_ID = " + version;
		
		try {
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(query);
			ResultSet rst = pstmt.executeQuery();
			ResultSetMetaData metaData = rst.getMetaData();			
			
			// By having a db select based on version number only one row of parameter names will
			// be retrieved
			rst.next();
			{
				for(int i = 2; i < metaData.getColumnCount(); i ++)
				{
					int columnIndex = i+1;										
					parameterName = metaData.getColumnName(columnIndex);
					String datatype = metaData.getColumnTypeName(columnIndex);

					parameterValue = rst.getString(columnIndex);
					if (datatype.equalsIgnoreCase("DATE") && parameterValue != null)
					{
						// The date comes in as CCYY-MM-DD 0:00:00
						dateString = parameterValue.substring(0,4) + parameterValue.substring(5,7) +  parameterValue.substring(8,10);
						parameterValue = dateString;
					}
					if (parameterValue == null)
					{
						parameterValue = "";
					}					
					parameterReader.assignParameter(parameterName, parameterValue);					
				}				
			}
			rst.close();
			pstmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return new ArrayList<Property>();		
	}
		
	public CSIParameterTableReader(Collection<DbRecord> Parameters) {
		super(Parameters);
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
	
}