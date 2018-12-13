package examples.location;

import examples.airport.Airport;

public interface AirportLocation extends Airport{
	Airport getAirport();
	float getLongitude();
	float getLattitude();
}
