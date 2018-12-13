package examples.location.inheritance;

import examples.airport.BaseAirportManager;

public class AirportLocationManager extends BaseAirportManager<AirportLocation> {
	private static AirportLocationManager airportLocationManager = new AirportLocationManager();

	public static AirportLocationManager get() {
		return airportLocationManager;
	}
		
	protected AirportLocationManager() {
		super();
	}

	protected  void buildRelationships(AirportLocation airportLocation) {
	}
	
	protected void deleteRelationships(AirportLocation airportLocation) {
	}
	
}
