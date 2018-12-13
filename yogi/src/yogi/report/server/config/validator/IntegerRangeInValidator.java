package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class IntegerRangeInValidator extends BaseValidator{

	private static final long serialVersionUID = 1L;
	
	public IntegerRangeInValidator() {
		super("^((([0-9]+)|(([0-9]+)-([0-9]+)))(,(([0-9]+)|(([0-9]+)-([0-9]+))))*)$","must be comma seperated numeric values or numeric value ranges");
	}
	
	public IntegerRangeInValidator(int lengthOfString) {		
		super("^((([0-9]{"+lengthOfString+"})|(([0-9]{"+lengthOfString+"})-([0-9]{"+lengthOfString+"})))(,(([0-9]{"+lengthOfString+"})|(([0-9]{"+lengthOfString+"})-([0-9]{"+lengthOfString+"}))))*)$","must be "+ lengthOfString + " characters comma seperated numeric values or numeric value ranges");
	}

}