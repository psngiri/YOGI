package examples.location.inheritance;

import examples.airport.Airport;

public interface AirportLocation extends Airport{
	float getLongitude();
	float getLattitude();
}
