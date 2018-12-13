package yogi.report.server.config.validator;

import yogi.report.server.config.BaseInValidator;

public class FloatInValidator extends BaseInValidator{

	private static final long serialVersionUID = 1L;
	
	public FloatInValidator() {
		super("^(([+\\-]?[0-9]*\\.?[0-9]+)|(([+\\-]?[0-9]*\\.?[0-9]+)-([+\\-]?[0-9]*\\.?[0-9]+)))+(,(([+\\-]?[0-9]*\\.?[0-9]+)|(([+\\-]?[0-9]*\\.?[0-9]+)-([+\\-]?[0-9]*\\.?[0-9]+))))*$", "must be comma seperated numeric values or numeric value ranges");
	}	
}