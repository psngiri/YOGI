package yogi.report.server.config.validator;

import yogi.report.server.config.BaseInValidator;

public class DoubleInValidator extends BaseInValidator{

	private static final long serialVersionUID = 1L;
	
	public DoubleInValidator() {
		super("^([-]?[0-9]*\\.?[0-9]+)(-[-]?[0-9]*\\.?[0-9]+)?((,[-]?[0-9]*\\.?[0-9]+)(-[-]?[0-9]*\\.?[0-9]+)?)*$","must be comma separated numeric values");
	}
}