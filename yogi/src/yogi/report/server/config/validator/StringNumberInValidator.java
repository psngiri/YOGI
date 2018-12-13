package yogi.report.server.config.validator;

import yogi.report.server.config.BaseInValidator;

public class StringNumberInValidator extends BaseInValidator{

	private static final long serialVersionUID = 1L;
	
	public StringNumberInValidator() {
		super("^([a-zA-Z0-9:]+)(,[a-zA-Z0-9:]+)*$","must be comma seperated alphanumeric values");
	}
	
	public StringNumberInValidator(int lengthOfString) {
		super("^([a-zA-Z0-9:]{"+lengthOfString+"})(,[a-zA-Z0-9:]{"+lengthOfString+"})*$"," must be comma seperated alphanumeric values of length : " + lengthOfString);
	}
}




