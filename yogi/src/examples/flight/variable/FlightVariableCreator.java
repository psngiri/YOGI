package examples.flight.variable;

import yogi.optimize.expr.Variable;
import yogi.optimize.expr.VariableCreator;

import examples.flight.Flight;

public class FlightVariableCreator implements VariableCreator<Flight> {

	public Variable create(Flight flight) {
		return new Variable(flight, 0f, Float.MAX_VALUE, "F" + flight.getName());
	}

}
