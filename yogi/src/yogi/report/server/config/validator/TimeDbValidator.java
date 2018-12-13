package yogi.report.server.config.validator;

import yogi.report.server.config.BaseInValidator;

public class TimeDbValidator extends BaseInValidator{

	private static final long serialVersionUID = 1L;

	public TimeDbValidator() {
		super("^(0?[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1,2,3,4,5][0-9]):(0[0-9]|[1,2,3,4,5][0-9])$", 
				"must be HH:MM:SS Format");
	}
	
}