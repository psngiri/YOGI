package yogi.report.server.config;

import yogi.report.condition.BaseInCondition;

public class BaseInValidator extends BaseValidator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseInValidator(String regex, String message) {
		this(regex, message, regex);
	}

	public BaseInValidator(String regex, String message, String javaScripRegex) {
		super(regex+"|"+BaseInCondition.filePatterString+"|"+BaseInCondition.queryPatterString, message+" or File:filename:numberofHeaderRows or Query:Query Name:User ID:Partition", javaScripRegex+"|"+yogi.report.condition.BaseInCondition.filePatterString+"|"+BaseInCondition.queryPatterString);
		
	}

}
