package examples.app;


import yogi.base.Util;
import yogi.base.app.ApplicationProperties;
import yogi.base.app.Executor;
import yogi.base.app.standard.StandardApplication;
import yogi.base.util.logging.LoggingPropertiesFileReader;
import yogi.optimize.expr.VariableAssistant;
import yogi.optimize.expr.io.MpsWriter;
import yogi.property.alias.io.PropertyAliasReader;

import examples.airport.io.AirportReader;
import examples.flight.Flight;
import examples.flight.io.FlightReader;
import examples.flight.variable.FlightVariableCreator;
import examples.timeline.app.MultiTimelineModule;

public class ExampleApplication extends StandardApplication {
	public static boolean RUN = true;
	

	public ExampleApplication(String dataLocation, String outputLocation) {
		super(dataLocation, outputLocation);
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		VariableAssistant.get().setVariableCreator(Flight.class, new FlightVariableCreator());
		addReader(new AirportReader(ApplicationProperties.InputDataLocation + "/airports.dat"));
		addReader(new FlightReader(ApplicationProperties.InputDataLocation + "/flights.dat"));
		
		addModule(new MultiTimelineModule());
		
		addWriter(new MpsWriter(ApplicationProperties.OutputLocation + "/matrix.mps"));
	}

	public static void main(String[] args) {
		LoggingPropertiesFileReader.RUN = false;
		PropertyAliasReader.RUN = false;
		Executor.get().execute(new ExampleApplication(Util.getTestDataDirectoryPath() + "/exampleTestData", null));
	}
}
