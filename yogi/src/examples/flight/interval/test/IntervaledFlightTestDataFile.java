package examples.flight.interval.test;

import java.util.List;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.Util;
import yogi.base.util.immutable.ImmutableList;
import yogi.base.util.logging.LoggingPropertiesFileReader;
import yogi.period.date.DateManager;
import yogi.period.frequency.FrequencyManager;
import yogi.period.interval.Interval;

import examples.airport.Airport;
import examples.airport.AirportManager;
import examples.airport.io.AirportReader;
import examples.flight.Flight;
import examples.flight.FlightManager;
import examples.flight.interval.FlightIntervalsAssistant;
import examples.flight.io.FlightReader;
import examples.retime.flight.RetimeFlight;
import examples.retime.flight.RetimeFlightCreator;

public class IntervaledFlightTestDataFile extends TestCase {
	private static String dataLocation = Util.getTestDataDirectoryPath() + "/exampleTestData";

	protected void setUp() throws Exception {
		LoggingPropertiesFileReader.RUN = false;
		super.setUp();
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
		
		Interval interval1 = new Interval(DateManager.get().getDate(2007, 1, 1), 
				DateManager.get().getDate(2007, 2, 15), FrequencyManager.AllDayFrequency);
		Interval interval2 = new Interval(DateManager.get().getDate(2007, 3, 1), 
				DateManager.get().getDate(2007, 4, 15), FrequencyManager.AllDayFrequency);
		FlightIntervalsAssistant.get().addInterval(dfwOrd, interval1);
		FlightIntervalsAssistant.get().addInterval(dfwOrd, interval2);
		FlightIntervalsAssistant.get().addInterval(ordDfw, interval1);
		
		ImmutableList<Interval> intervalsDfwOrd = FlightIntervalsAssistant.get().getIntervals(dfwOrd);
		assertEquals(2, intervalsDfwOrd.size());
		assertEquals(interval1, intervalsDfwOrd.get(0));
		assertEquals(interval2, intervalsDfwOrd.get(1));
		ImmutableList<Interval> intervalsOrdDfw = FlightIntervalsAssistant.get().getIntervals(ordDfw);
		assertEquals(1, intervalsOrdDfw.size());
		assertEquals(interval1, intervalsOrdDfw.get(0));
		RetimeFlightCreator retimeFlightCreator = new RetimeFlightCreator();
		retimeFlightCreator.setCandidateId(1);
		retimeFlightCreator.setFlight(dfwOrd);
		RetimeFlight dfwOrd1 = retimeFlightCreator.create();
		ImmutableList<Interval> intervalsDfwOrd1 = FlightIntervalsAssistant.get().getIntervals(dfwOrd1);
		assertEquals(2, intervalsDfwOrd1.size());
		assertEquals(interval1, intervalsDfwOrd1.get(0));
		assertEquals(interval2, intervalsDfwOrd1.get(1));

		retimeFlightCreator = new RetimeFlightCreator();
		retimeFlightCreator.setCandidateId(1);
		retimeFlightCreator.setFlight(ordDfw);
		RetimeFlight ordDfw1 = retimeFlightCreator.create();
		FlightIntervalsAssistant.get().addInterval(ordDfw1, interval2);
		FlightIntervalsAssistant.get().addInterval(ordDfw1, interval1);
		ImmutableList<Interval> intervalsOrdDFw1 = FlightIntervalsAssistant.get().getIntervals(ordDfw1);
		assertEquals(2, intervalsOrdDFw1.size());
		System.out.println(intervalsOrdDFw1);
		System.out.println(interval1);
		System.out.println(interval2);
		assertEquals(interval1, intervalsOrdDFw1.get(1));	
		assertEquals(interval2, intervalsOrdDFw1.get(0));
		}

}
