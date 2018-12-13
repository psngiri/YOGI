package yogi.property;

import yogi.base.validation.ValidatorAdapter;

public class PropertyValidator extends ValidatorAdapter<Property> {

	@Override
	public boolean validate(Property property) {
		PropertyName propertyName = property.getPropertyName();
		if(propertyName == null) return false;
		if(!checkNotNull(propertyName.getClassName(), "ClassName")) return false;
		if(!checkNotNull(propertyName.getName(), "Name")) return false;
		if(!checkNotNull(property.getValue(), "Value")) return false;
		return true;
	}
	private boolean checkNotNull(Object object, String message) {
		if(object == null)
		{
			error("Null Propery: " + message);
			return false;
		}
		return true;
	}
}
