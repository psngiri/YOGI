package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class FrequencyValidator extends BaseValidator{

	private static final long serialVersionUID = 1L;

	public FrequencyValidator() {
		super("^(|[1234567MTWQFJS]+)$", "must be of the format 1234567 or MTWQFJS");
	}
	
}