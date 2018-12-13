package examples.airport;

import yogi.base.validation.ValidatorAdapter;

public class AirportValidator extends ValidatorAdapter<Airport> {

	public boolean validate(Airport object) {
		if(object.getCode() == null) return false;
		return super.validate(object);
	}
}
