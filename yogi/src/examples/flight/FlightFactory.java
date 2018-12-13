package examples.flight;

import yogi.base.Factory;

public class FlightFactory extends Factory<Flight> {
	private static FlightFactory flightFactory = new FlightFactory(FlightManager.get());


	protected FlightFactory(FlightManager flightManager) {
		super(flightManager);
	}


	public static FlightFactory get() {
		return flightFactory;
	}
}
