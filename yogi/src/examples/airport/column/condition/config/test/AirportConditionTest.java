package examples.airport.column.condition.config.test;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;

import examples.airport.AirportManager;
import examples.airport.column.condition.config.AirportInConditionConfig;
import examples.airport.column.condition.config.AirportNotInConditionConfig;

public class AirportConditionTest extends TestCase{
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		TestErrorReporter.end();
		Factory.clearAllFactories();			
	}
	
	public void testAirportCondition() {
				
		AirportInConditionConfig  inConditionConfig= new AirportInConditionConfig();
		assertTrue(inConditionConfig.getValidator().validate("DFW"));
		assertTrue(inConditionConfig.getValidator().validate("DFW,NYC"));	
		assertFalse(inConditionConfig.getValidator().validate("null"));
		assertFalse(inConditionConfig.getValidator().validate("xyza"));

		assertTrue(inConditionConfig.getCondition("BDL").satisfied(AirportManager.get().getAirport("BDL")));
		assertFalse(inConditionConfig.getCondition("DFW").satisfied(AirportManager.get().getAirport("DAL")));

		assertTrue(inConditionConfig.getCondition("DAL").satisfied(AirportManager.get().getAirport("DAL")));
		assertFalse(inConditionConfig.getCondition("DAL").satisfied(AirportManager.get().getAirport("DFW")));

		assertTrue(inConditionConfig.getCondition("NYC").satisfied(AirportManager.get().getAirport("NYC")));
		assertFalse(inConditionConfig.getCondition("NYC").satisfied(AirportManager.get().getAirport("JFK")));

		assertTrue(inConditionConfig.getCondition("JFK").satisfied(AirportManager.get().getAirport("JFK")));
		assertFalse(inConditionConfig.getCondition("JFK").satisfied(AirportManager.get().getAirport("NYC")));

		assertTrue(inConditionConfig.getCondition("NYC,DFW").satisfied(AirportManager.get().getAirport("NYC")));
		assertTrue(inConditionConfig.getCondition("NYC,DFW").satisfied(AirportManager.get().getAirport("DFW")));
		assertFalse(inConditionConfig.getCondition("NYC,DFW").satisfied(AirportManager.get().getAirport("JFK")));
		assertFalse(inConditionConfig.getCondition("NYC,DFW").satisfied(AirportManager.get().getAirport("DAL")));
		
		assertTrue(inConditionConfig.getCondition("sea,sjd").satisfied(AirportManager.get().getAirport("SEA")));
		assertTrue(inConditionConfig.getCondition("sea,sjd").satisfied(AirportManager.get().getAirport("SJD")));

		AirportNotInConditionConfig  notInConditionConfig= new AirportNotInConditionConfig();
		assertTrue(notInConditionConfig.getValidator().validate("DFW"));
		assertTrue(notInConditionConfig.getValidator().validate("DFW,NYC"));
		assertFalse(notInConditionConfig.getValidator().validate("abcd"));
		assertFalse(notInConditionConfig.getValidator().validate("efgh"));
		
		assertFalse(notInConditionConfig.getCondition("DFW").satisfied(AirportManager.get().getAirport("DFW")));
		assertTrue(notInConditionConfig.getCondition("DFW").satisfied(AirportManager.get().getAirport("DAL")));
		
		assertFalse(notInConditionConfig.getCondition("DAL").satisfied(AirportManager.get().getAirport("DAL")));
		assertTrue(notInConditionConfig.getCondition("DAL").satisfied(AirportManager.get().getAirport("DFW")));
		
		assertFalse(notInConditionConfig.getCondition("NYC").satisfied(AirportManager.get().getAirport("NYC")));
		assertTrue(notInConditionConfig.getCondition("NYC").satisfied(AirportManager.get().getAirport("JFK")));
		
		assertFalse(notInConditionConfig.getCondition("JFK").satisfied(AirportManager.get().getAirport("JFK")));
		assertTrue(notInConditionConfig.getCondition("JFK").satisfied(AirportManager.get().getAirport("NYC")));
		
		assertFalse(notInConditionConfig.getCondition("NYC,DFW").satisfied(AirportManager.get().getAirport("NYC")));
		assertFalse(notInConditionConfig.getCondition("NYC,DFW").satisfied(AirportManager.get().getAirport("DFW")));
		assertTrue(notInConditionConfig.getCondition("NYC,DFW").satisfied(AirportManager.get().getAirport("JFK")));
		assertTrue(notInConditionConfig.getCondition("NYC,DFW").satisfied(AirportManager.get().getAirport("DAL")));
		
		assertTrue(notInConditionConfig.getCondition("sea,sjd").satisfied(AirportManager.get().getAirport("NYC")));
		assertTrue(notInConditionConfig.getCondition("sea,sjd").satisfied(AirportManager.get().getAirport("AUS")));
	}

}