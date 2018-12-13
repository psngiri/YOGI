package yogi.base.app.standard;

import yogi.base.app.ApplicationProperties;
import yogi.base.app.BaseApplication;
import yogi.property.parameter.io.ParameterReader;

public abstract class StandardApplication extends BaseApplication {
	private String dataLocation;
	private String outputLocation;
	
	public StandardApplication(String dataLocation, String outputLocation) {
		super();
		this.dataLocation = dataLocation;
		setOutputLocation(outputLocation);
		setupApplicationProperties();
	}

	private void setupApplicationProperties() {
		ApplicationProperties.InputDataLocation = this.dataLocation;
		ApplicationProperties.OutputLocation = this.outputLocation;
	}

	private void setOutputLocation(String outputLocation) {
		if(outputLocation == null)
		{
			this.outputLocation = dataLocation + "/output";
		}else
		{
			this.outputLocation = outputLocation;
		}
		
	}

	public void setup() {	
		addPropertyFile("*/config/commonProperties.dat");
		addPropertyFile("*/config/properties.dat");
		addPropertyFile(dataLocation + "/properties.dat");
		
	    addReader(new ParameterReader(dataLocation + "/parameter.txt"));
	}

	public String getDataLocation() {
		return dataLocation;
	}

	public String getOutputLocation() {
		return outputLocation;
	}

	@Override
	public void exit(int exitCode) {
		super.exit(exitCode);
		System.exit(exitCode);
	}

}
