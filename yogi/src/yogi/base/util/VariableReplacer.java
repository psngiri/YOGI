package yogi.base.util;

import yogi.base.app.ErrorReporter;

public abstract class VariableReplacer {

	public String replaceVariables(String record)
	{
		int startIndex = record.indexOf("${");
		if(startIndex == -1) return record;
		int endIndex = record.indexOf("}", startIndex);
		if(endIndex == -1) ErrorReporter.get().error("Wrongly formed property substitution ${ has to be closed by } :", record);
		String variableName = record.substring(startIndex+2, endIndex);
		String value = getValue(variableName);
		if(value == null) throw new RuntimeException("Could not find value for variableName: "+ variableName);
		String variable = record.substring(startIndex, endIndex + 1);
		String newRecord = record.replace(variable, value);
		return replaceVariables(newRecord);
	}

	protected abstract String getValue(String variableName);
}
