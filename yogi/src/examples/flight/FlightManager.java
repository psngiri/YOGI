package examples.flight;

import yogi.base.relationship.RelationshipManager;
import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.base.util.immutable.ImmutableList;

import examples.airport.Airport;


public class FlightManager extends RelationshipManager<Flight>{
	private static OneToManyInverseRelationship<Airport, Flight> arrivingAirport = RelationshipTypeFactory.get().createOneToManyInverseRelationship(Airport.class, Flight.class, "Arriving");
	private static OneToManyInverseRelationship<Airport, Flight> departingAirport = RelationshipTypeFactory.get().createOneToManyInverseRelationship(Airport.class, Flight.class, "Departing");
	private static FlightManager flightManager = new FlightManager();
		
	protected FlightManager() {
		super();
	}

	public static FlightManager get() {
		return flightManager;
	}

	protected  void buildRelationships(Flight flight) {
		buildRelationship(flight.getArrivalAirport(), flight, arrivingAirport);
		buildRelationship(flight.getDepartureAirport(), flight, departingAirport);
	}
	
	protected void deleteRelationships(Flight flight) {
		deleteRelationship(flight.getArrivalAirport(), flight, arrivingAirport);
		deleteRelationship(flight.getDepartureAirport(), flight, departingAirport);
	}
	
	public ImmutableList<Flight> getArrivingFlights(Airport airport)
	{
		return getRelationship(airport, arrivingAirport);
	}
	
	public ImmutableList<Flight> getDepartingFlights(Airport airport)
	{
		return getRelationship(airport, departingAirport);
	}
}
