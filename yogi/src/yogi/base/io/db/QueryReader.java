package yogi.base.io.db;

import java.util.HashMap;
import java.util.Map;

import yogi.base.io.FileToStringReader;
import yogi.base.io.resource.SystemResource;
import yogi.property.KeyAndPropertyReplacer;

public class QueryReader extends FileToStringReader {
	Map<String, String> variables;
	public QueryReader(SystemResource resource) {
		super(resource, 0);
		variables = new HashMap<String, String>();
	}

	public void addVariable(String variableName, String value)
	{
		variables.put(variableName, value);
	}
	
	@Override
	protected String adjust(String line) {
		String rtnValue = super.adjust(line);
		rtnValue = rtnValue.trim();
		rtnValue = new KeyAndPropertyReplacer(variables).replaceVariables(rtnValue);
		rtnValue = rtnValue + " ";
		return rtnValue;
	}

}
