package examples.airport.column.formatter;

import yogi.base.io.Formatter;

import examples.airport.Airport;

public class AirportFormatter implements Formatter<Airport> {

	@Override
	public String format(Airport object) {
		return object.getCode();
	}

}
