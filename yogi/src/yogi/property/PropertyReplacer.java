package yogi.property;

import yogi.base.util.VariableReplacer;

public class PropertyReplacer extends VariableReplacer {
	public static boolean UseSystemProperty = true;
	@Override
	protected String getValue(String variableName)  {
		if(UseSystemProperty){
			String property = System.getenv(variableName);
			if(property != null) return property;
		}
		Object value = PropertyManager.getPropertyValue(variableName);
		return value.toString();
	}
}
