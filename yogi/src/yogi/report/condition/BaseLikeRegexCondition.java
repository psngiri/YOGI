package yogi.report.condition;

import java.util.ArrayList;
import java.util.Scanner;

public class BaseLikeRegexCondition<C> extends BaseLikeCondition<C>{
	
	ArrayList<String> includes = new ArrayList<String>();
	ArrayList<String> excludes = new ArrayList<String>();
	
	public BaseLikeRegexCondition(String value) {
		this(value, ',');
	}
	
	public BaseLikeRegexCondition(String value, char separator) {
		super(value, separator);
	}
	
	@Override
	protected boolean checkPattern(String compareStr, String pattern) {
		return compareStr.matches(pattern);
	}
	
}
