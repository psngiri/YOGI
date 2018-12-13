package yogi.server.app;

import yogi.base.app.ApplicationProperties;
import yogi.base.app.BaseApplication;
import yogi.base.app.Executor;
import yogi.base.app.error.ErrorsLoggerThreadFormatter;
import yogi.remote.server.app.CommandServerModule;

/**
 * @author Vikram Vadavala
 *
 */
public class BaseServerApplication extends BaseApplication {

	private String dataLocation;
	private String outputLocation;
	
	public BaseServerApplication(String dataLocation,String outputLocation) {
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
		Executor.get().getErrorReporterLogListener().setFormatter(new ErrorsLoggerThreadFormatter());
		this.addModule(new CommandServerModule());
	}

	public String getDataLocation() {
		return dataLocation;
	}

	public String getOutputLocation() {
		return outputLocation;
	}

	@Override
	public void exit(int exitCode) {
		if(exitCode != 0) System.exit(exitCode);
		System.out.println("Listening for Commands");
	}

}
