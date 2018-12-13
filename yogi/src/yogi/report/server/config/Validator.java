package yogi.report.server.config;

import java.io.Serializable;

public interface Validator extends Serializable{
	boolean validate(String value);
	String getRegEx();
	String getMessage();
}
