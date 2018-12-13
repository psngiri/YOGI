package examples.location;

import yogi.base.relationship.RelationshipManager;
import yogi.base.relationship.types.OneToOneInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;

import examples.airport.Airport;

public class AirportLocationManager extends RelationshipManager<AirportLocation> {
	private static AirportLocationManager airportLocationManager = new AirportLocationManager();
	private static OneToOneInverseRelationship<Airport, AirportLocation> location = RelationshipTypeFactory.get().createOneToOneInverseRelationship(Airport.class, AirportLocation.class, "LOCATION");

	public static AirportLocationManager get() {
		return airportLocationManager;
	}
		
	protected AirportLocationManager() {
		super();
	}

	protected  void buildRelationships(AirportLocation airportLocation) {
		buildRelationship(airportLocation.getAirport(), airportLocation, location);
	}
	
	protected void deleteRelationships(AirportLocation airportLocation) {
		deleteRelationship(airportLocation.getAirport(), airportLocation, location);
	}
	
	public AirportLocation getAirportLocation(Airport airport)
	{
		return getRelationship(airport, location);
	}

}
