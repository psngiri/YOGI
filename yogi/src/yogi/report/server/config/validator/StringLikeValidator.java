package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class StringLikeValidator extends BaseValidator{

	private static final long serialVersionUID = 1L;
	
	public StringLikeValidator() {
		super("^([~%?\\w*\\s/\\-]+)(,[~%?\\w*\\s/\\-]+)*$", "must be string patterns seperated by commas, % in the pattern reprasents on or more any character, and ? for any character, those are the only 2 special characters supported in a pattern");
	}
}