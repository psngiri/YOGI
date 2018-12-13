package examples.retime.flight;

import yogi.base.Factory;

public class RetimeFlightFactory extends Factory<RetimeFlight> {
	private static RetimeFlightFactory itsInstance = new RetimeFlightFactory(RetimeFlightManager.get());

	protected RetimeFlightFactory(RetimeFlightManager manager) {
		super(manager);
	}

	public static RetimeFlightFactory get() {
		return itsInstance;
	}
}
