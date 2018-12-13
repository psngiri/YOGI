package examples.airport.io;

import yogi.base.io.Formatter;

import examples.airport.Airport;

public class AirportFormatter implements Formatter<Airport> {

	public String format(Airport object) {
		return object.getCode();
	}

}
