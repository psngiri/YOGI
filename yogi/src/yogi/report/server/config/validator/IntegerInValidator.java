package yogi.report.server.config.validator;

import yogi.report.server.config.BaseInValidator;

public class IntegerInValidator extends BaseInValidator{

	private static final long serialVersionUID = 1L;
	
	public IntegerInValidator() {
		this("must be comma seperated numeric values or numeric value ranges");
	}
	
	public IntegerInValidator(String message) {
		super("^((([0-9]+)|(([0-9]+)-([0-9]+)))(,(([0-9]+)|(([0-9]+)-([0-9]+))))*)$", message);
	}
	
	public IntegerInValidator(int lengthOfString) {
		this("must be "+ lengthOfString + " characters comma seperated numeric values or numeric value ranges");
	}

	public IntegerInValidator(int lengthOfString, String message) {
		super("^((([0-9]{"+lengthOfString+"})|(([0-9]{"+lengthOfString+"})-([0-9]{"+lengthOfString+"})))(,(([0-9]{"+lengthOfString+"})|(([0-9]{"+lengthOfString+"})-([0-9]{"+lengthOfString+"}))))*)$", message);
	}
}