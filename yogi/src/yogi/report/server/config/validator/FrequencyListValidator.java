package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class FrequencyListValidator extends BaseValidator{

	private static final long serialVersionUID = 1L;

	public FrequencyListValidator() {
		this("must contain comma seperated values of the format MTWQFJS");
	}
	
	public FrequencyListValidator(String msg) {
		super("^(|[MTWQFJS]+)(,(|[MTWQFJS]+))*$", msg);
	}
}