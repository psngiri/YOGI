package yogi.base.app.standard;

import yogi.base.app.ApplicationProperties;
import yogi.base.app.BaseApplication;
import yogi.base.io.resource.db.DbResource;
import yogi.base.io.resource.db.SimpleDbResource;
import yogi.csi.CSIApplicaitonStatusUpdater;
import yogi.csi.CSIApplicationStatusUpdaterAdapter;
import yogi.property.parameter.io.db.CSIParameterReader;

public abstract class StandardCSIApplication extends BaseApplication {
	public static String CsiJDBCUrl="jdbc:oracle:thin:cposapp/cposapp@raptor.corp.amrcorp.com:1521:cpd";
	private String outputLocation;
	private String inputLocation;
	private DbResource csiDbResource ;
	private CSIParameterReader parameterReader;
	private CSIApplicaitonStatusUpdater statusUpdater;
	
	public StandardCSIApplication(String dataLocation, String userID, String taskID) {
		this(dataLocation, userID, taskID, null);
	}
	
	public StandardCSIApplication(String dataLocation, String userID, String taskID, String outputLocation) {
		super();
		this.inputLocation = dataLocation;
		if(userID != null)this.inputLocation = this.inputLocation+ "/" + userID;
		if(taskID != null)this.inputLocation = this.inputLocation+ "/" + taskID;
		if(outputLocation != null) this.outputLocation = outputLocation;
		else this.outputLocation = inputLocation + "/output";
		setupApplicationProperties(userID, taskID);
		csiDbResource = new SimpleDbResource("${yogi.base.app.standard.StandardCSIApplication:CsiJDBCUrl}");
	    parameterReader = new CSIParameterReader(csiDbResource,taskID);
	    statusUpdater = new CSIApplicationStatusUpdaterAdapter();
	}

	private void setupApplicationProperties(String userID, String taskID) {
		if(userID != null) ApplicationProperties.EmployeeID = userID;
		if(taskID != null) ApplicationProperties.TaskID = taskID;
		if(inputLocation != null) ApplicationProperties.InputDataLocation = this.inputLocation;
		if(outputLocation != null) ApplicationProperties.OutputLocation = this.outputLocation;
	}

	public DbResource getCsiDbResource() {
		return csiDbResource;
	}
	
	public void addTableNameVersionParameterName(String tableName, String versionParameterName)
	{
		parameterReader.addTableNameVersionParameterName(tableName, versionParameterName);
	}	

	public void setup() {	
		addPropertyFile("*/config/commonProperties.dat");
		addPropertyFile("*/config/properties.dat");
		addPropertyFile(inputLocation + "/../../properties.dat");
		addPropertyFile(inputLocation + "/../properties.dat");
		addPropertyFile(inputLocation + "/properties.dat");
		addReader(parameterReader);
	}


	public String getOutputLocation() {
		return outputLocation;
	}
	@Override
	public void exit(int exitCode) {
		super.exit(exitCode);
		statusUpdater.exit(exitCode);
		System.exit(exitCode);
	}

	@Override
	public void start() {
		statusUpdater.start();
		super.start();
	}

}
