package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class LongValidator extends BaseValidator{

	private static final long serialVersionUID = 1L;

	public LongValidator() {
		super("^[0-9]+$", "must be an integer value");
	}
	public LongValidator(int size) {
		super("^[0-9]{"+size+"}$", "must be an integer value");
	}
}