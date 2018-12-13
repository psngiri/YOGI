package examples.airport.column.condition.config;

import yogi.report.condition.Condition;
import yogi.report.server.config.ConditionConfig;

import examples.airport.Airport;
import examples.airport.column.condition.AirportInCondition;
import examples.airport.column.validator.AirportColumnValidator;

public class AirportInConditionConfig extends ConditionConfig<Airport> {

	private static final long serialVersionUID = 1L;

	public AirportInConditionConfig() {
		super("In",new AirportColumnValidator());
	}

	@Override
	public Condition<Airport> getCondition(String value) {
		return new AirportInCondition(value.toUpperCase());
	}

}
