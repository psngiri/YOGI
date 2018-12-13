package yogi.report.server.config.validator;

import yogi.report.server.config.BaseInValidator;

public class TimeValidator extends BaseInValidator{

	private static final long serialVersionUID = 1L;

	public TimeValidator() {
		super("^(([0]?[0-9]|1[0-9]|2[0-4]):(0[0-9]|[1,2,3,4,5][0-9]))+(,(0[0-9]|1[0-9]|2[0-4]):(0[0-9]|[1,2,3,4,5][0-9]))*$", 
				"must be HH:MM Format comma seperated values");
	}
	
}