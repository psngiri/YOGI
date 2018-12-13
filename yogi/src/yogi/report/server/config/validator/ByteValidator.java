package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class ByteValidator extends BaseValidator{

	private static final long serialVersionUID = 1L;

	public ByteValidator() {
		super("^([0-9]|[1][0-9][0-9]|[2][0-4][0-9]|[2][5][0-5])$", "must be numeric between 0 to 256");		
	}	
}