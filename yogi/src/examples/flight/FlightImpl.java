package examples.flight;
import yogi.base.relationship.RelationshipObjectImpl;

import examples.airport.Airport;

public class FlightImpl extends RelationshipObjectImpl<Flight> implements Flight{
	private Airport departureAirport;
	private Airport arrivalAirport;
	
	
	FlightImpl(Airport departureAirport, Airport arrivalAirport) {
		super();
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public String getName()
	{
		return departureAirport.getName() + arrivalAirport.getName();
	}

	@Override
	public String toString() {
		return getName();
	}

}
