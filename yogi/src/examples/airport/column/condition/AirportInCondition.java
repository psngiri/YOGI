package examples.airport.column.condition;

import yogi.report.condition.BaseInCondition;

import examples.airport.Airport;
import examples.airport.AirportManager;

public class AirportInCondition extends BaseInCondition<Airport> {

	public AirportInCondition(String value) {
		super(value);
	}

	public AirportInCondition(String value, char separator) {
		super(value, separator);
	}

	@Override
	public Airport scan(String token) {
		return AirportManager.get().getAirport(token);
	}
}