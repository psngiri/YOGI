package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class BooleanValidator extends BaseValidator {
	
	private static final long serialVersionUID = 1L;

	public BooleanValidator() {
		super("^(T|F|t|f|TRUE|FALSE|True|False|true|false)$", "must be a boolean value");
	}
	
}
