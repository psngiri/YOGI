package examples.location;

import yogi.base.validation.ValidatorAdapter;

public class AirportLocationValidator extends ValidatorAdapter<AirportLocation> {

	public boolean validate(AirportLocation object) {
		if(object.getCode() == null) return false;
		return true;
	}

}
