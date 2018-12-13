package yogi.property.parameter;

import yogi.base.Creator;
import yogi.base.ObjectNotFoundException;
import yogi.base.app.ErrorReporter;
import yogi.property.Property;
import yogi.property.PropertyCreator;
import yogi.property.PropertyManager;
import yogi.property.PropertyValidator;
import yogi.property.alias.PropertyAlias;
import yogi.property.alias.PropertyAliasManager;

public class ParameterAssistant {

	public static Property assignParameter(String parameterName, String parameterValue, PropertyValidator propertyValidator)
	{
		Creator<Property> creator = create(parameterName, parameterValue);
		Property property = creator.create();
		if(!propertyValidator.validate(property)) return null;
		PropertyManager.assignProperty(property);
		return property;
	}
	
	public static Creator<Property> create(String parameterName, String parameterValue) {
		String alias = parameterName.trim();
		String value = parameterValue.trim();
		PropertyCreator creator = new PropertyCreator();
		creator.setValue(value);
		try {
			PropertyAlias propertyAlias = PropertyAliasManager.get().getPropertyAlias(alias);
			creator.setPropertyName(propertyAlias.getPropertyName());
			return creator;
		} catch (ObjectNotFoundException e) {
			ErrorReporter.get().error("Could not find an Alias for parameter", parameterName);
		}
		return creator;
	}
}
