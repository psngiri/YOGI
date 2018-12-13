package yogi.base.test;

import junit.framework.TestCase;

import yogi.base.Implementation;
import yogi.base.app.testing.TestErrorReporter;
import yogi.base.relationship.RelationshipObject;

import examples.flight.Flight;
import examples.flight.FlightCreator;
import examples.retime.flight.RetimeFlight;
import examples.retime.flight.RetimeFlightCreator;
import examples.timeline.Timeline;

public class ImplementationTest extends TestCase {

	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		TestErrorReporter.end();
	}

	public void testIsOfType()
	{
		FlightCreator flightCreator = new FlightCreator();
		flightCreator.setArrivalAirportCode("DFW");
		flightCreator.setDepartureAirportCode("ORD");
		Flight flight = flightCreator.create();
		RetimeFlightCreator retimeFlightCreator = new RetimeFlightCreator();
		retimeFlightCreator.setCandidateId(1);
		retimeFlightCreator.setFlight(flight);
		RetimeFlight retimeFlight = retimeFlightCreator.create();
		assertTrue(Implementation.isOfType(Flight.class, retimeFlight));
		assertTrue(Implementation.isOfType(RetimeFlight.class, retimeFlight));
		assertTrue(!Implementation.isOfType(Timeline.class, retimeFlight));
		assertTrue(Implementation.isOfType(RelationshipObject.class, retimeFlight));
	}
}
