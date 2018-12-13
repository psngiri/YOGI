package examples.airport;

import yogi.base.Factory;



public class AirportFactory extends Factory<Airport> {
	private static AirportFactory airportFactory = new AirportFactory(AirportManager.get());

	protected AirportFactory(AirportManager manager) {
		super(manager);
	}

	public static AirportFactory get() {
		return airportFactory;
	}

}
