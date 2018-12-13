package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class StringNumberLikeValidator extends BaseValidator{

	private static final long serialVersionUID = 1L;
	
	public StringNumberLikeValidator() {
		super("^([~%?a-zA-Z0-9]+)(,[~%?a-zA-Z0-9]+)*$", "must be string patterns seperated by comma and containing [~ ? %] characters");
	}

}