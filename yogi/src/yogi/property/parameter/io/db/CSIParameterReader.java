package yogi.property.parameter.io.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import yogi.base.app.ApplicationProperties;
import yogi.base.io.FileWriter;
import yogi.base.io.MultiReader;
import yogi.base.io.Reader;
import yogi.base.io.db.dump.DumpProperties;
import yogi.base.io.resource.db.DbResource;
import yogi.property.Property;
import yogi.property.PropertyValidator;
import yogi.property.parameter.ParameterAssistant;
import yogi.property.parameter.io.ParameterReader;

public class CSIParameterReader implements Reader<Property> {
	MultiReader<Property> csiParameterReaders = new MultiReader<Property>();
	private DbResource csiDbResource;
	private String taskID;
	private HashMap<String, String> tableNameVersionParameterNameMap = new HashMap<String, String>();
	private PropertyValidator propertyValidator = new PropertyValidator();
	FileWriter<String> dumpWriter;
	List<Property> propertiesRead;
	

	public CSIParameterReader(DbResource csiDbResource, String taskID) {
		super();
		this.csiDbResource = csiDbResource;
		this.taskID = taskID;
	}
	
	private String getDumpFileName() {
		return ApplicationProperties.InputDataLocation + DumpProperties.getDumpLocation() + "parameters.dump";
	}

	public HashMap<String, String> getTableNameParameterNameMap() {
		return tableNameVersionParameterNameMap;
	}

	public void addTableNameVersionParameterName(String tableName, String versionParameterName)
	{
		tableNameVersionParameterNameMap.put(tableName, versionParameterName);
	}
	
	public List<Property> read() 
	{
		if(DumpProperties.ReadFromDbDump) return readFromDump();
		open();
		Connection connection = csiDbResource.getConnection();
		String parameterTableName;
		String version;
		String query = "SELECT TABLE_NM,VRSN_ID FROM SCNRIO_MAP WHERE ACTVTY_ID = " + taskID;
		try {
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(query);
			ResultSet rst = pstmt.executeQuery();			

			while (rst.next())
			{	
				parameterTableName = rst.getString(1);
				version = rst.getString(2);				
				setTableVersionIDproperties(parameterTableName,version,csiDbResource);
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
		csiParameterReaders.read();
		close();
		return propertiesRead;
    }
    
	private List<Property> readFromDump() {
		ParameterReader parameterReader = new ParameterReader(getDumpFileName());
		return parameterReader.read();
	}

	public void setTableVersionIDproperties(String parameterTableName, String version, DbResource csiDbResource)
	{
		String versionParameterName = tableNameVersionParameterNameMap.get(parameterTableName);
		if(versionParameterName != null)
		{
			assignParameter(versionParameterName, version);
		}
		else
		{			
			csiParameterReaders.addReader(new CSIParameterTableReader(csiDbResource,parameterTableName,version, this));			
		}
	}

	public void assignParameter(String parameterName, String parameterValue) {
		if(dumpWriter != null) dumpWriter.write(parameterName + " " + parameterValue);
		Property property = ParameterAssistant.assignParameter(parameterName, parameterValue, propertyValidator);
		propertiesRead.add(property);
	}


	public boolean isActivated() {
		return true;
	}

	private void close() {
		if(dumpWriter != null) dumpWriter.close();
	}

	private void open() {
		if(DumpProperties.WriteToDbDump)
		{
			dumpWriter = new FileWriter<String>(getDumpFileName());
			dumpWriter.open();
		}
		propertiesRead = new ArrayList<Property>();
	}

}
