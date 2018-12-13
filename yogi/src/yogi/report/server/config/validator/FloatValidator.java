package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class FloatValidator extends BaseValidator{

	private static final long serialVersionUID = 1L;

	public FloatValidator() {
		super("^[+\\-]?[0-9]*\\.?[0-9]+$", "must be a floating point numeric value");
	}
	
}