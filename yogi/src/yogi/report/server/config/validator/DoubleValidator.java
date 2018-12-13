package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class DoubleValidator extends BaseValidator{

	private static final long serialVersionUID = 1L;

	public DoubleValidator() {
		super("^[+\\-]?[0-9]*\\.?[0-9]+$", "must be a double numeric value");
	}
	
}