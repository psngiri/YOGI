package examples.airport.column.validator;

import yogi.report.server.config.BaseValidator;


public class AirportColumnValidator extends BaseValidator {

private static final long serialVersionUID = 1L;
	
	public AirportColumnValidator() {
		super("^$|^([a-zA-Z0-9:\\s]{3})(,[a-zA-Z0-9:\\s]{3})*$"," must be comma seperated alphanumeric values of length :3" );
	}

}
