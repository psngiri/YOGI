package yogi.property;

import yogi.base.ObjectNotFoundException;
import yogi.property.alias.PropertyAlias;
import yogi.property.alias.PropertyAliasManager;

public class PropertyAssistant {
	public static PropertyName getPropertyname(String propertyName) {
		String splitChar = null;
		boolean field = true;
		if(propertyName.contains(":"))
		{
			splitChar = ":";
		}else if(propertyName.contains(";"))
		{
			splitChar = ";";
			field = false;
		}else
		{
			try {
				PropertyAlias propertyAlias = PropertyAliasManager.get().getPropertyAlias(propertyName);
				return propertyAlias.getPropertyName();
			} catch (ObjectNotFoundException e) {
				throw new RuntimeException("Expects either : or ; separating the ClassName and the field or method name respectively or an Alias: "+ propertyName);
			}
		}
		String[] s1 = propertyName.split(splitChar);
		if(s1.length != 2) throw new RuntimeException(splitChar + " ambiguously placed count: " + s1.length);
		String className = s1[0].trim();
		String name = s1[1].trim();
		return new PropertyName(className, name, field);
	}

}
