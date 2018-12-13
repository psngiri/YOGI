package yogi.server.gui.app;

import yogi.base.app.ApplicationProperties;
import yogi.base.app.BaseApplication;
import yogi.base.app.Executor;
import yogi.base.app.error.ErrorsLoggerThreadFormatter;
import yogi.base.io.resource.db.DbResource;
import yogi.remote.server.app.CommandServerModule;
import yogi.server.util.BaseServerAssistant;

public class GuiServerApplication extends BaseApplication {

	private String dataLocation;
	private String outputLocation;
	
	public GuiServerApplication(String dataLocation,String outputLocation) {
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
		DbResource ngpDbResource = BaseServerAssistant.get().getDbResource();
		addModules(ngpDbResource);
	}

	protected void addModules(DbResource ngpDbResource)
	{
	
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
	
	public static void main(String[] args)
	{
		if(args.length < 1) {
			System.out.println("Usage: GuiServerApplication inputLocation [outputLocation]");
			System.out.println("Note: [outputLocation] is optional");
			return;
		}
		
		String outputLocation = null;
		if(args.length == 2) outputLocation = args[1];
		
		GuiServerApplication preStrategiesServerApplication = new GuiServerApplication(args[0], outputLocation);
		preStrategiesServerApplication.execute();
	}

}
