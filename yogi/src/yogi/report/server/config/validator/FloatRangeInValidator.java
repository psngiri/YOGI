package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class FloatRangeInValidator extends BaseValidator{

	private static final long serialVersionUID = 1L;
	
	public FloatRangeInValidator() {
		super("^(([+\\-]?[0-9]*\\.?[0-9]+)|(([+\\-]?[0-9]*\\.?[0-9]+)-([+\\-]?[0-9]*\\.?[0-9]+)))+(,(([+\\-]?[0-9]*\\.?[0-9]+)|(([+\\-]?[0-9]*\\.?[0-9]+)-([+\\-]?[0-9]*\\.?[0-9]+))))*$", "must be comma seperated numeric values or numeric value ranges");
	}	
}