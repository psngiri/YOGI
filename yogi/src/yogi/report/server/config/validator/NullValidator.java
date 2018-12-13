package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class NullValidator extends BaseValidator {
	
	private static final long serialVersionUID = 1L;

	public NullValidator() {
		super(".*", "Value not required.");
	}
	
}