package yogi.property;

import java.util.Map;

import yogi.base.util.VariableReplacer;


public class KeyAndPropertyReplacer extends VariableReplacer {
	Map<String, String> keys;
	
	
	public KeyAndPropertyReplacer(Map<String, String> keys) {
		super();
		this.keys = keys;
	}


	@Override
	protected String getValue(String variableName)  {
		String value = keys.get(variableName);
		if(value == null){
			Object propertyValue = PropertyManager.getPropertyValue(variableName);
			value = propertyValue.toString();
		}
		return value;
	}

}
