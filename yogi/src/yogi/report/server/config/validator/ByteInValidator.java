package yogi.report.server.config.validator;

import yogi.report.server.config.BaseInValidator;

public class ByteInValidator extends BaseInValidator{

	private static final long serialVersionUID = 1L;
	
	public ByteInValidator() {
		super("^([0-9]+)(,[0-9]+)*$","must be comma seperated numeric values");
	}
	
	public ByteInValidator(int lengthOfString) {
		super("^([0-9]{"+lengthOfString+"})(,[0-9]{"+lengthOfString+"})*$","must be "+lengthOfString+" characters comma seperated numeric values");
	}

}