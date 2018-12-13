package examples.location;

import yogi.base.Factory;
import yogi.base.relationship.RelationshipManager;


public class AirportLocationFactory extends Factory<AirportLocation> {
	private static AirportLocationFactory airportLocationFactory = new AirportLocationFactory(AirportLocationManager.get());
	

	protected AirportLocationFactory(RelationshipManager<AirportLocation> relationshipManager) {
		super(relationshipManager);
	}


	public static AirportLocationFactory get() {
		return airportLocationFactory;
	}
	
}
