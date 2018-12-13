package examples.airport.column.condition;

import examples.airport.Airport;

public class AirportNotInCondition extends AirportInCondition {

	public AirportNotInCondition(String value) {
		super(value);
	}

	public AirportNotInCondition(String value, char separator) {
		super(value, separator);
	}
	
	@Override
	public boolean satisfied(Airport data) {
		return !super.satisfied(data);
	}

}