package examples.app;

import yogi.base.Util;
import yogi.base.app.ApplicationProperties;
import yogi.base.app.Executor;
import yogi.base.app.standard.StandardApplication;
import yogi.base.util.logging.LoggingPropertiesFileReader;
import yogi.optimize.expr.VariableAssistant;
import yogi.optimize.expr.io.MpsWriter;
import yogi.property.alias.io.PropertyAliasReader;

import examples.airport.AirportManager;
import examples.flight.Flight;
import examples.flight.io.FlightReader;
import examples.flight.variable.FlightVariableCreator;
import examples.location.inheritance.AirportLocationFactory;
import examples.location.inheritance.io.AirportLocationReader;
import examples.timeline.app.TimelineModule;

public class ExampleApplication1 extends StandardApplication {
	public static boolean RUN = true;
	

	public ExampleApplication1(String dataLocation, String outputLocation) {
		super(dataLocation, outputLocation);
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		VariableAssistant.get().setVariableCreator(Flight.class, new FlightVariableCreator());
		AirportManager.get().setFactory(AirportLocationFactory.get());
		addReader(new AirportLocationReader(ApplicationProperties.InputDataLocation + "/airportLocations.dat"));
		addReader(new FlightReader(ApplicationProperties.InputDataLocation + "/flights.dat"));
		
		addModule(new TimelineModule());
		
		addWriter(new MpsWriter(ApplicationProperties.OutputLocation + "/inheritance/matrix.mps"));
	}

	public static void main(String[] args) {
		LoggingPropertiesFileReader.RUN = false;
		PropertyAliasReader.RUN = false;
		Executor.get().execute(new ExampleApplication1(Util.getTestDataDirectoryPath() + "/exampleTestData", null));
	}
}
