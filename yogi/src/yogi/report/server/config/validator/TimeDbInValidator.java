package yogi.report.server.config.validator;

import yogi.report.server.config.BaseInValidator;

public class TimeDbInValidator extends BaseInValidator{

	private static final long serialVersionUID = 1L;
	private final static String timeRange =  "((0?[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1,2,3,4,5][0-9]):(0[0-9]|[1,2,3,4,5][0-9]))([-](0?[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1,2,3,4,5][0-9]):(0[0-9]|[1,2,3,4,5][0-9]))";
	private final static String timeSingle = "((0?[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1,2,3,4,5][0-9]):(0[0-9]|[1,2,3,4,5][0-9]))";

	public TimeDbInValidator() {
		super("^(" + timeRange + "|" + timeSingle + "[ ]?)(,[ ]?(" + timeRange + "|" + timeSingle + ")[ ]?)*$", 
				"must be HH:MM:SS Format comma seperated values");
	}
	
}