package examples.flight;

import yogi.base.relationship.RelationshipObject;

import examples.airport.Airport;

public interface Flight extends RelationshipObject{
	Airport getDepartureAirport();
	Airport getArrivalAirport();
}
