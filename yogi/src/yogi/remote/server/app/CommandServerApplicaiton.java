package yogi.remote.server.app;

import yogi.base.app.ApplicationProperties;
import yogi.base.app.BaseApplication;
import yogi.base.relationship.types.capacity.io.CapacityReader;
import yogi.base.relationship.types.capacity.io.CapacityWriter;
import yogi.base.relationship.types.io.RelationshipTypeWriter;

public class CommandServerApplicaiton extends BaseApplication {

	private String dataLocation;
	private String outputLocation;
	
	public CommandServerApplicaiton(String dataLocation, String outputLocation) {
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
		
		this.addModule(new CommandServerModule());
		CapacityReader.RUN = false;
		CapacityWriter.RUN = false;
		RelationshipTypeWriter.RUN = false;
	}

	public String getDataLocation() {
		return dataLocation;
	}

	public String getOutputLocation() {
		return outputLocation;
	}
	
	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("Usage: CommandServerApplicaiton inputLocation [outputLocation]");
			return;
		}
		String outputLocation = null;
		if(args.length == 2) outputLocation = args[1];
		CommandServerApplicaiton commandServerApplicaiton = new CommandServerApplicaiton(args[0], outputLocation);
		commandServerApplicaiton.execute();
	}

	@Override
	public void exit(int exitCode) {
		System.out.println("Listening for Commands");
	}

}
