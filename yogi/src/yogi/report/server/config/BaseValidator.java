package yogi.report.server.config;

import java.util.regex.Pattern;

public class BaseValidator implements Validator {

	private static final long serialVersionUID = 1L;
	private String javaScriptRegex;
	private String regex;
	private String message;
	transient private Pattern pattern;

	public BaseValidator(String regex, String message ) {
		this(regex, message, regex);
	}

	public BaseValidator(String regex, String message,String javaScripRegex ) {
		super();
		this.regex = regex;
		this.message = message;
		this.pattern =  Pattern.compile(regex);
		this.javaScriptRegex = javaScripRegex;
	}

	public String getJavaScriptRegex() {
		return javaScriptRegex;
	}

	@Override
	public String getRegEx() {
		return regex;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	@Override
	public boolean validate(String value) {		
    	   if (value == null) {
                return false;
           }    		
    	   if (pattern.matcher(value).find()) {
                return true;
           }    
           return false;
	}
}
