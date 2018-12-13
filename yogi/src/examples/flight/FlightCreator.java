package examples.flight;

import examples.airport.AirportManager;

import yogi.base.Creator;

import examples.airport.Airport;


public class FlightCreator implements Creator<Flight> {
	private String departureAirportCode;
	private String arrivalAirportCode;
	
	public Flight create() {
		Airport departureAirport =AirportManager.get().getAirport(departureAirportCode);
	    Airport arrivalAirport = AirportManager.get().getAirport(arrivalAirportCode);
		Flight flight = new FlightImpl(departureAirport, arrivalAirport);
		return flight;
	}

	public String getArrivalAirportCode() {
		return arrivalAirportCode;
	}

	public String getDepartureAirportCode() {
		return departureAirportCode;
	}

	public void setArrivalAirportCode(String arrivaleAirportCode) {
		this.arrivalAirportCode = arrivaleAirportCode;
	}

	public void setDepartureAirportCode(String departureAirportCode) {
		this.departureAirportCode = departureAirportCode;
	}
	
}
