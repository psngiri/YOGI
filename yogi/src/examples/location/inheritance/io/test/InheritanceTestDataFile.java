package examples.location.inheritance.io.test;

import java.util.List;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.Util;
import yogi.base.io.SystemOutWriter;
import yogi.base.util.logging.LoggingPropertiesFileReader;
import yogi.optimize.expr.Constraint;
import yogi.optimize.expr.ConstraintFactory;

import examples.airport.Airport;
import examples.airport.AirportManager;
import examples.flight.Flight;
import examples.flight.FlightFactory;
import examples.flight.FlightManager;
import examples.flight.io.FlightReader;
import examples.location.inheritance.AirportLocation;
import examples.location.inheritance.AirportLocationFactory;
import examples.location.inheritance.AirportLocationManager;
import examples.location.inheritance.io.AirportLocationReader;
import examples.timeline.TimelineFactory;
import examples.timeline.TimelineManager;
import examples.timeline.bc.TimelineFlightConstraintGenerator;
import examples.timeline.bp.TimelineGenerator;

public class InheritanceTestDataFile extends TestCase {
	private static String dataLocation = Util.getTestDataDirectoryPath() + "/exampleTestData";
	public InheritanceTestDataFile(String arg0) {
		super(arg0);
		LoggingPropertiesFileReader.RUN = false;
		//		Logger.getLogger("com.aa.cp").setLevel(Level.OFF);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		Factory.clearAllFactories();
	}
	
	public void test()
	{
		AirportManager.get().setFactory(AirportLocationFactory.get());
		AirportLocationReader airportLocationReader = new AirportLocationReader(dataLocation + "/airportLocations.dat");
		airportLocationReader.read();
		Airport dfw = AirportManager.get().getAirport("DFW");
		Airport ord = AirportManager.get().getAirport("ORD");
		assertEquals("DFW", dfw.getCode());
		assertEquals("ORD", ord.getCode());
		
		AirportLocation dfwLocation = AirportLocationManager.get().getAirport("DFW");
		AirportLocation ordLocation = AirportLocationManager.get().getAirport("ORD");
		assertEquals("DFW", dfwLocation.getCode());
		assertEquals("ORD", ordLocation.getCode());
		assertEquals(10f, dfwLocation.getLattitude());
		assertEquals(20.5f, ordLocation.getLattitude());
		assertEquals(11.5f, dfwLocation.getLongitude());
		assertEquals(20f, ordLocation.getLongitude());
		
		FlightReader flightReader = new FlightReader(dataLocation + "/flights.dat");
		flightReader.read();
		List<Flight> arrivingFlightsDfw = FlightManager.get().getArrivingFlights(dfw);
		List<Flight> arrivingFlightsOrd = FlightManager.get().getArrivingFlights(ord);
		List<Flight> departingFlightsDfw = FlightManager.get().getDepartingFlights(dfw);
		List<Flight> departingFlightsOrd = FlightManager.get().getDepartingFlights(ord);
		assertEquals(1, arrivingFlightsDfw.size());
		assertEquals(1, arrivingFlightsOrd.size());
		assertEquals(1, departingFlightsDfw.size());
		assertEquals(1, departingFlightsOrd.size());
		Flight dfwOrd = arrivingFlightsOrd.get(0);
		Flight ordDfw = arrivingFlightsDfw.get(0);
		assertEquals(dfwOrd, departingFlightsDfw.get(0));
		assertEquals(ordDfw, departingFlightsOrd.get(0));
		
		TimelineGenerator timelineGenerator = new TimelineGenerator();
		timelineGenerator.run();
		assertEquals(dfw, TimelineManager.get().getTimeline(dfw).getAirport());
		assertEquals(ord, TimelineManager.get().getTimeline(ord).getAirport());
		arrivingFlightsDfw = TimelineManager.get().getTimeline(dfw).getArrivingFlights();
		arrivingFlightsOrd = TimelineManager.get().getTimeline(ord).getArrivingFlights();
		departingFlightsDfw = TimelineManager.get().getTimeline(dfw).getDepartingFlights();
		departingFlightsOrd = TimelineManager.get().getTimeline(ord).getDepartingFlights();

		assertEquals(1, arrivingFlightsDfw.size());
		assertEquals(1, arrivingFlightsOrd.size());
		assertEquals(1, departingFlightsDfw.size());
		assertEquals(1, departingFlightsOrd.size());
		dfwOrd = arrivingFlightsOrd.get(0);
		ordDfw = arrivingFlightsDfw.get(0);

		assertEquals(dfwOrd, departingFlightsDfw.get(0));
		assertEquals(ordDfw, departingFlightsOrd.get(0));
		
		assertEquals(TimelineManager.get().getTimeline(ord), TimelineManager.get().getArrivingTimeline(dfwOrd));
		assertEquals(TimelineManager.get().getTimeline(dfw), TimelineManager.get().getDepartingTimeline(dfwOrd));
		assertEquals(TimelineManager.get().getTimeline(dfw), TimelineManager.get().getArrivingTimeline(ordDfw));
		assertEquals(TimelineManager.get().getTimeline(ord), TimelineManager.get().getDepartingTimeline(ordDfw));
		TimelineFactory.get().delete(TimelineManager.get().getTimeline(dfw));
		assertEquals(null, TimelineManager.get().getTimeline(dfw));
		assertEquals(null, TimelineManager.get().getDepartingTimeline(dfwOrd));
		assertEquals(null, TimelineManager.get().getArrivingTimeline(ordDfw));
		try {
			AirportLocationFactory.get().delete(ordLocation);
			AirportLocationFactory.get().delete(dfwLocation);
			fail("Should throw Exception");
		} catch (RuntimeException e) {
		}
		FlightFactory.get().delete(dfwOrd);
		assertEquals(0, FlightManager.get().getArrivingFlights(ord).size());
		assertEquals(1, FlightManager.get().getDepartingFlights(ord).size());
		assertEquals(1, FlightManager.get().getArrivingFlights(dfw).size());
		assertEquals(0, FlightManager.get().getDepartingFlights(dfw).size());
		assertEquals(0, TimelineManager.get().getTimeline(ord).getArrivingFlights().size());
		assertEquals(1, TimelineManager.get().getTimeline(ord).getDepartingFlights().size());
		
		FlightFactory.get().delete(ordDfw);
		assertEquals(0, FlightManager.get().getArrivingFlights(dfw).size());
		assertEquals(0, FlightManager.get().getDepartingFlights(dfw).size());
		assertEquals(0, FlightManager.get().getArrivingFlights(ord).size());
		assertEquals(0, FlightManager.get().getDepartingFlights(ord).size());
		assertEquals(0, TimelineManager.get().getTimeline(ord).getArrivingFlights().size());
		assertEquals(0, TimelineManager.get().getTimeline(ord).getDepartingFlights().size());
		AirportLocationFactory.get().delete(dfwLocation);
		try {
			AirportLocationFactory.get().delete(ordLocation);
			fail("Should throw Exception");
		} catch (RuntimeException e) {
		}
		TimelineFactory.get().delete(TimelineManager.get().getTimeline(ord));
		AirportLocationFactory.get().delete(ordLocation);
		TimelineFlightConstraintGenerator timelineFlightConstraintGenerator = new TimelineFlightConstraintGenerator();
		timelineFlightConstraintGenerator.run();
		ConstraintFactory.get().write(new SystemOutWriter<Constraint>());
		
	}
	
	
}
