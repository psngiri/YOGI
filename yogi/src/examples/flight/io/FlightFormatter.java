package examples.flight.io;

import yogi.base.io.Formatter;

import examples.flight.Flight;

public class FlightFormatter implements Formatter<Flight> {

	public String format(Flight object) {
		return String.format("%1$s %2$s", object.getDepartureAirport().getCode(), object.getArrivalAirport().getCode());
	}

}
