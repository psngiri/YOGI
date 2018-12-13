package yogi.report.condition;

import yogi.base.io.Formatter;

public interface Condition<C> {
	void setFormatter(Formatter<? super C> formatter);
	void setUserId(String userId);
	boolean satisfied(C data);
	String getSqlValue(String columnName);
}
