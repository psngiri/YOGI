package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class StringValidator extends BaseValidator{

	private static final long serialVersionUID = 1L;

	public StringValidator() {
		super("^([\\w*\\s/\\-]+)", "must be a string value");
	}
	
	public StringValidator(int lengthOfString) {
		super("^([\\w*\\s/\\-]+){"+lengthOfString+"}$", "must be "+lengthOfString+" characters string value");
	}
	
}