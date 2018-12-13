package examples.airport;

import yogi.base.ObjectNotFoundException;
import yogi.base.app.ErrorReporter;

public class AirportManager extends BaseAirportManager<Airport> {
	private static AirportManager airportManager = new AirportManager();
	private AirportCreator airportCreator = new AirportCreator();
	protected AirportManager() {
		super();
	}

	public static AirportManager get() {
		return airportManager;
	}

	@Override
	protected void buildRelationships(Airport object) {
	}

	@Override
	protected void deleteRelationships(Airport object) {
	}

	public Airport getAirport(String airportCode) {
		try {
			return this.getObject(airportCode);
		} catch (ObjectNotFoundException e) {
			return createAirport(airportCode);
		}
	}

	private synchronized Airport createAirport(String airportCode) {
		try {
			return this.getObject(airportCode);
		} catch (ObjectNotFoundException e) {
			ErrorReporter.get().info("Creating Airport", airportCode);
			
			airportCreator.setCode(airportCode);
			return AirportFactory.get().create(airportCreator);
		}
	}
}
