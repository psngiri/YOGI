package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class StringNumberValidator extends BaseValidator{

	private static final long serialVersionUID = 1L;

	public StringNumberValidator() {
		super("^[a-zA-Z0-9]+$", "must be a string value");
	}
	
	public StringNumberValidator(int lengthOfString) {
		super("^[a-zA-Z0-9]{"+lengthOfString+"}$", "must be "+lengthOfString+" characters value");
	}
	
}