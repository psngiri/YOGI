package yogi.property.alias;

import yogi.base.Assistant;

public class PropertyAliasAssistant extends Assistant<PropertyAlias> {
	private static PropertyAliasAssistant propertyAliasAssistant = new PropertyAliasAssistant();

	public static PropertyAliasAssistant get() {
		return propertyAliasAssistant;
	}
	
	public void setAlias(String propertyAliasName, Class<?> klass, String name, boolean field)
	{
		setAlias(propertyAliasName, klass.getName(), name, field);
	}
	
	public void setAlias(String propertyAliasName, String className, String name, boolean field)
	{
		PropertyAliasCreator creator = new PropertyAliasCreator();
		creator.setValue(propertyAliasName);
		creator.setClassName(className);
		creator.setName(name);
		creator.setField(field);
		PropertyAliasFactory.get().create(creator);
	}
}
