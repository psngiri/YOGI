package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class TimeRangesValidator extends BaseValidator{

	private static final long serialVersionUID = 1L;

	public TimeRangesValidator() {
		super("^((0[0-9]|1[0-9]|2[0-3])(0[0-9]|[1,2,3,4,5][0-9])-(0[0-9]|1[0-9]|2[0-3])(0[0-9]|[1,2,3,4,5][0-9]))+(,(0[0-9]|1[0-9]|2[0-3])(0[0-9]|[1,2,3,4,5][0-9])-(0[0-9]|1[0-9]|2[0-3])(0[0-9]|[1,2,3,4,5][0-9])+)*$", 
				"must be HHMM-HHMM Format comma seperated values");
	}
	
}