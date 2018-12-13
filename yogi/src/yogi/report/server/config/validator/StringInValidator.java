package yogi.report.server.config.validator;

import yogi.report.server.config.BaseInValidator;

public class StringInValidator extends BaseInValidator{

	private static final long serialVersionUID = 1L;
	
	public StringInValidator() {
		super("^([\\w*\\s/\\-]+)(,[\\w*\\s/\\-]+)*$","must be comma seperated values");
	}
	
	public StringInValidator(int lengthOfString) {
		super("^([\\w*\\s/\\-]{"+lengthOfString+"})(,[\\w*\\s/\\-]{"+lengthOfString+"})*$","must be "+lengthOfString+" characters comma seperated values");
	}
}