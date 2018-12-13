package yogi.property.parameter;

import yogi.base.validation.ValidatorAdapter;
import yogi.property.Property;
import yogi.property.PropertyName;

public class ParameterValidator extends ValidatorAdapter<Property> {

	@Override
	public boolean validate(Property property) {
		PropertyName propertyName = property.getPropertyName();
		if(propertyName == null) return false;
		if(propertyName.getClassName() == null || propertyName.getName() == null || property.getValue() == null) return false;
		return true;
	}

}
