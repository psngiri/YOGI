package examples.location.inheritance;

import yogi.base.Factory;



public class AirportLocationFactory extends Factory<AirportLocation> {
	private static AirportLocationFactory airportLocationFactory = new AirportLocationFactory(AirportLocationManager.get());
	
	protected AirportLocationFactory(AirportLocationManager airportLocationManager) {
		super(airportLocationManager);
	}

	public static AirportLocationFactory get() {
		return airportLocationFactory;
	}
	
}
