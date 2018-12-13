package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class StringNumberHyphenInValidator extends BaseValidator{

	private static final long serialVersionUID = 1L;
	
	public StringNumberHyphenInValidator() {
		super("^([a-zA-Z0-9:\\s/-]+)(,[a-zA-Z0-9:\\s/-]+)*$","must be comma seperated alphanumeric values");
	}
	public StringNumberHyphenInValidator(int lengthOfString) {
		super("^([a-zA-Z0-9:\\s/-]{"+lengthOfString+"})(,[a-zA-Z0-9:\\s/-]{"+lengthOfString+"})*$"," must be comma seperated alphanumeric values of length : " + lengthOfString);
	}
	
}




