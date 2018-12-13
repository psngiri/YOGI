package test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import junit.framework.TestCase;

import yogi.base.Creator;
import yogi.base.Factory;
import yogi.base.FactoryListenerAdapter;
import yogi.base.Selector;
import yogi.base.Util;
import yogi.base.app.Executor;
import yogi.base.io.FileWriter;
import yogi.base.io.SystemOutWriter;
import yogi.base.stats.timer.Timer;
import yogi.base.util.logging.LoggingPropertiesFileReader;
import yogi.optimize.expr.Constraint;
import yogi.optimize.expr.ConstraintFactory;
import yogi.optimize.expr.VariableAssistant;
import yogi.optimize.expr.io.MpsWriter;
import yogi.property.Property;
import yogi.property.PropertyManager;
import yogi.property.io.PropertyReader;
import yogi.property.io.PropertyScanner;

import examples.airport.Airport;
import examples.airport.AirportFactory;
import examples.airport.AirportManager;
import examples.airport.io.AirportFormatter;
import examples.airport.io.AirportReader;
import examples.flight.Flight;
import examples.flight.FlightAssistant;
import examples.flight.FlightFactory;
import examples.flight.FlightManager;
import examples.flight.io.FlightFormatter;
import examples.flight.io.FlightReader;
import examples.flight.variable.FlightVariableCreator;
import examples.location.AirportLocation;
import examples.location.AirportLocationFactory;
import examples.location.AirportLocationManager;
import examples.location.io.AirportLocationReader;
import examples.retime.flight.RetimeFlight;
import examples.retime.flight.RetimeFlightAssistant;
import examples.retime.flight.RetimeFlightCreator;
import examples.timeline.Timeline;
import examples.timeline.TimelineFactory;
import examples.timeline.TimelineManager;
import examples.timeline.bc.TimelineFlightConstraintGenerator;
import examples.timeline.bp.TimelineGenerator;

public class MainTestDataFile extends TestCase {
	private static String dataLocation = Util.getTestDataDirectoryPath() + "/exampleTestData";
	public static int value = 10;
	public static String stringValue = "test";
	public static void setValue(String object)
	{
		value = Integer.parseInt(object);
	}
	
	public MainTestDataFile(String arg0) {
		super(arg0);
		LoggingPropertiesFileReader.RUN = false;
		//		Logger.getLogger("com.aa.cp").setLevel(Level.OFF);
		VariableAssistant.get().setVariableCreator(Flight.class, new FlightVariableCreator());
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		Factory.clearAllFactories();
	}
	
	public void test1()
	{
		AirportReader airportReader = new AirportReader(dataLocation + "/airports.dat");
		airportReader.read();
		Airport dfw = AirportManager.get().getAirport("DFW");
		Airport ord = AirportManager.get().getAirport("ORD");
		assertEquals("DFW", dfw.getCode());
		assertEquals("ORD", ord.getCode());
		
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
		
		TimelineFlightConstraintGenerator timelineFlightConstraintGenerator = new TimelineFlightConstraintGenerator();
		timelineFlightConstraintGenerator.run();
		ConstraintFactory.get().write(new SystemOutWriter<Constraint>());
		AirportFormatter airportFormatter = new AirportFormatter();
		FileWriter<Airport> airportWriter = new FileWriter<Airport>(dataLocation + "/output/airports.dat", airportFormatter);
		airportWriter.open();
		AirportFactory.get().write(airportWriter);
		airportWriter.close();
		FlightFormatter flightFormatter = new FlightFormatter();
		FileWriter<Flight> flightWriter = new FileWriter<Flight>(dataLocation + "/output/flights.dat", flightFormatter);
		flightWriter.open();
		FlightFactory.get().write(flightWriter);
		flightWriter.close();
		MpsWriter mpsWriter = new MpsWriter(dataLocation + "/output/matrix.mps");
		mpsWriter.write();
		airportWriter = new FileWriter<Airport>(dataLocation + "/output/filteredAirports.dat", airportFormatter);
		airportWriter.open();
		AirportFactory.get().write(airportWriter, new Selector<Airport>(){

			public boolean select(Airport item) {
				return (item.getCode().equals("DFW"));
			}
		}, null);
		airportWriter.close();
	}
	public void test2()
	{
		AirportLocationReader airportLocationReader = new AirportLocationReader(dataLocation + "/airportLocations.dat");
		airportLocationReader.read();
		Airport dfw = AirportManager.get().getAirport("DFW");
		Airport ord = AirportManager.get().getAirport("ORD");
		assertEquals("DFW", dfw.getCode());
		assertEquals("ORD", ord.getCode());
		
		AirportLocation dfwLocation = AirportLocationManager.get().getAirportLocation(dfw);
		AirportLocation ordLocation = AirportLocationManager.get().getAirportLocation(ord);
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
			AirportFactory.get().delete(ord);
			AirportFactory.get().delete(dfw);
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
		AirportFactory.get().delete(dfw);
		AirportLocationFactory.get().delete(ordLocation);
		try {
			AirportFactory.get().delete(ord);
			fail("Should throw Exception");
		} catch (RuntimeException e) {
		}
		TimelineFactory.get().delete(TimelineManager.get().getTimeline(ord));
		AirportFactory.get().delete(ord);
		TimelineFlightConstraintGenerator timelineFlightConstraintGenerator = new TimelineFlightConstraintGenerator();
		timelineFlightConstraintGenerator.run();
		ConstraintFactory.get().write(new SystemOutWriter<Constraint>());
		
	}
	
	public void test3()
	{
		PropertyScanner propertyScanner = new PropertyScanner();
		Creator<Property> creator = propertyScanner.scan("test.MainTest:value=20");
		Property property = creator.create();
		PropertyManager.assignProperty(property);
		assertEquals(20, value);
		creator = propertyScanner.scan("test.MainTest;setValue=30");
		property = creator.create();
		PropertyManager.assignProperty(property);
		assertEquals(30, value);
		creator = propertyScanner.scan("test.MainTest:stringValue=my test");
		property = creator.create();
		PropertyManager.assignProperty(property);
		assertEquals("my test", stringValue);
	}
	
	public void test4()
	{
		TimelineGenerator timelineGenerator = new TimelineGenerator();
		assertEquals(true, timelineGenerator.isActivated());
		assertEquals(Level.INFO, Timer.DefaultLevel);
		Executor executor = Executor.get();
		executor.execute(new PropertyReader(dataLocation + "/properties.dat"));
		assertEquals(false, timelineGenerator.isActivated());
		assertEquals(Level.SEVERE, Timer.DefaultLevel);
		TimelineGenerator.RUN = true;
		Timer.DefaultLevel = Level.INFO;
	}
	
	public void test5()
	{
		AirportLocationReader airportLocationReader = new AirportLocationReader(dataLocation + "/airportLocations.dat");
		airportLocationReader.read();
		Airport dfw = AirportManager.get().getAirport("DFW");
		Airport ord = AirportManager.get().getAirport("ORD");
		assertEquals("DFW", dfw.getCode());
		assertEquals("ORD", ord.getCode());
		
		AirportLocation dfwLocation = AirportLocationManager.get().getAirportLocation(dfw);
		AirportLocation ordLocation = AirportLocationManager.get().getAirportLocation(ord);
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
		AirportFactoryListener airportFactoryListener = new AirportFactoryListener();
		AirportFactory.get().addFactoryListener(airportFactoryListener);
		AirportFactory.get().delete(dfw);
		AirportFactory.get().removeFactoryListener(airportFactoryListener);
		assertEquals(0, FlightManager.get().getArrivingFlights(ord).size());
		assertEquals(0, FlightManager.get().getDepartingFlights(ord).size());
		assertEquals(0, FlightManager.get().getArrivingFlights(dfw).size());
		assertEquals(0, FlightManager.get().getDepartingFlights(dfw).size());
		assertEquals(0, TimelineManager.get().getTimeline(ord).getArrivingFlights().size());
		assertEquals(0, TimelineManager.get().getTimeline(ord).getDepartingFlights().size());
		
		assertEquals(0, FlightManager.get().getArrivingFlights(dfw).size());
		assertEquals(0, FlightManager.get().getDepartingFlights(dfw).size());
		assertEquals(0, FlightManager.get().getArrivingFlights(ord).size());
		assertEquals(0, FlightManager.get().getDepartingFlights(ord).size());
		assertEquals(0, TimelineManager.get().getTimeline(ord).getArrivingFlights().size());
		assertEquals(0, TimelineManager.get().getTimeline(ord).getDepartingFlights().size());
		AirportLocationFactory.get().delete(ordLocation);
		try {
			AirportFactory.get().delete(ord);
			fail("Should throw Exception");
		} catch (RuntimeException e) {
		}
		TimelineFactory.get().delete(TimelineManager.get().getTimeline(ord));
		AirportFactory.get().delete(ord);
		TimelineFlightConstraintGenerator timelineFlightConstraintGenerator = new TimelineFlightConstraintGenerator();
		timelineFlightConstraintGenerator.run();
		ConstraintFactory.get().write(new SystemOutWriter<Constraint>());
		
	}
	public void test6()
	{
		AirportLocationReader airportLocationReader = new AirportLocationReader(dataLocation + "/airportLocations.dat");
		airportLocationReader.read();
		Airport dfw = AirportManager.get().getAirport("DFW");
		Airport ord = AirportManager.get().getAirport("ORD");
		assertEquals("DFW", dfw.getCode());
		assertEquals("ORD", ord.getCode());
		
		AirportLocation dfwLocation = AirportLocationManager.get().getAirportLocation(dfw);
		AirportLocation ordLocation = AirportLocationManager.get().getAirportLocation(ord);
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
		Timeline timelineDFW = TimelineManager.get().getTimeline(dfw);
		assertEquals(dfw, timelineDFW.getAirport());
		Timeline timelineORD = TimelineManager.get().getTimeline(ord);
		assertEquals(ord, timelineORD.getAirport());
		arrivingFlightsDfw = timelineDFW.getArrivingFlights();
		arrivingFlightsOrd = timelineORD.getArrivingFlights();
		departingFlightsDfw = timelineDFW.getDepartingFlights();
		departingFlightsOrd = timelineORD.getDepartingFlights();

		assertEquals(1, arrivingFlightsDfw.size());
		assertEquals(1, arrivingFlightsOrd.size());
		assertEquals(1, departingFlightsDfw.size());
		assertEquals(1, departingFlightsOrd.size());
		dfwOrd = arrivingFlightsOrd.get(0);
		ordDfw = arrivingFlightsDfw.get(0);

		assertEquals(dfwOrd, departingFlightsDfw.get(0));
		assertEquals(ordDfw, departingFlightsOrd.get(0));
		
		assertEquals(timelineORD, TimelineManager.get().getArrivingTimeline(dfwOrd));
		assertEquals(timelineDFW, TimelineManager.get().getDepartingTimeline(dfwOrd));
		assertEquals(timelineDFW, TimelineManager.get().getArrivingTimeline(ordDfw));
		assertEquals(timelineORD, TimelineManager.get().getDepartingTimeline(ordDfw));
		
		try {
			timelineORD.addArrivingFlight(ordDfw);
			fail("Exception expected");
		} catch (RuntimeException e) {
		}

	}
	public void testDerivedRelationships()
	{
		AirportReader airportReader = new AirportReader(dataLocation + "/airports.dat");
		airportReader.read();
		Airport dfw = AirportManager.get().getAirport("DFW");
		Airport ord = AirportManager.get().getAirport("ORD");
		assertEquals("DFW", dfw.getCode());
		assertEquals("ORD", ord.getCode());
		
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
		RetimeFlightCreator retimeFlightCreator = new RetimeFlightCreator();
		retimeFlightCreator.setCandidateId(1);
		retimeFlightCreator.setFlight(dfwOrd);
		RetimeFlight dfwOrd1 = retimeFlightCreator.create();
		retimeFlightCreator.setCandidateId(2);
		RetimeFlight dfwOrd2 = retimeFlightCreator.create();
		retimeFlightCreator.setCandidateId(1);
		retimeFlightCreator.setFlight(ordDfw);
		RetimeFlight ordDfw1 = retimeFlightCreator.create();
		retimeFlightCreator.setCandidateId(2);
		RetimeFlight ordDfw2 = retimeFlightCreator.create();
		assertEquals("DFWORD",FlightAssistant.get().getMarket(dfwOrd));
		assertEquals("DFWORD",FlightAssistant.get().getMarketDontCompute(dfwOrd1));
		assertEquals("DFWORD",FlightAssistant.get().getMarketDontCompute(dfwOrd2));
		assertEquals(null,FlightAssistant.get().getMarketDontCompute(ordDfw1));
		assertEquals("ORDDFW",FlightAssistant.get().getMarket(ordDfw));
		assertEquals("ORDDFW",FlightAssistant.get().getMarketDontCompute(ordDfw2));
		RetimeFlightAssistant.get().setRetimePenalty(dfwOrd1, 1000);
		assertEquals(new Integer(1000), RetimeFlightAssistant.get().getRetimePenalty(dfwOrd1));
		assertEquals(null, RetimeFlightAssistant.get().getRetimePenalty(dfwOrd2));
		assertEquals(dfwOrd, FlightAssistant.get().getParent(dfwOrd1));
		assertEquals(dfwOrd, FlightAssistant.get().getRoot(dfwOrd1));
		assertEquals(null, FlightAssistant.get().getParent(dfwOrd));
		assertEquals(dfwOrd, FlightAssistant.get().getRoot(dfwOrd));
		retimeFlightCreator.setCandidateId(11);
		retimeFlightCreator.setFlight(dfwOrd1);
		RetimeFlight dfwOrd11 = retimeFlightCreator.create();
		assertEquals(dfwOrd1, FlightAssistant.get().getParent(dfwOrd11));
		assertEquals(dfwOrd, FlightAssistant.get().getRoot(dfwOrd11));
		assertEquals(dfwOrd1, FlightAssistant.get().getParent(dfwOrd11, 1));
		try {
			assertEquals(dfwOrd1, FlightAssistant.get().getParent(dfwOrd11, 0));
			fail("Exception expected for order 0");
		} catch (RuntimeException e) {}
		assertEquals(dfwOrd1, FlightAssistant.get().getParent(dfwOrd11, 1));
		assertEquals(dfwOrd, FlightAssistant.get().getParent(dfwOrd11, 2));
		try {
			assertEquals(dfwOrd1, FlightAssistant.get().getParent(dfwOrd11, 3));
			fail("Exception expected when parent is null");
		} catch (RuntimeException e) {}
		assertEquals("DFWORD",FlightAssistant.get().getMarketDontCompute(dfwOrd11));
		assertEquals(new Integer(1000), RetimeFlightAssistant.get().getRetimePenalty(dfwOrd11));
		RetimeFlightAssistant.get().setRetimePenalty(dfwOrd11, 2000);
		assertEquals(new Integer(2000), RetimeFlightAssistant.get().getRetimePenalty(dfwOrd11));		
	}

	static class AirportFactoryListener extends FactoryListenerAdapter<Airport>
	{

		public boolean delete(Airport airport) {
			List<Flight> arrivingFlights = new ArrayList<Flight>(FlightManager.get().getArrivingFlights(airport));
			for(Flight arrivingFlight: arrivingFlights)
			{
				FlightFactory.get().delete(arrivingFlight);
			}
			List<Flight> departingFlights = new ArrayList<Flight>(FlightManager.get().getDepartingFlights(airport));
			for(Flight departingFlight: departingFlights)
			{
				FlightFactory.get().delete(departingFlight);
			}
			AirportLocation airportLocation = AirportLocationManager.get().getAirportLocation(airport);
			AirportLocationFactory.get().delete(airportLocation);
			return true;
		}
		
	}
	
}
