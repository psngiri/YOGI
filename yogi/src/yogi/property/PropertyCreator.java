package yogi.property;

import yogi.base.Creator;

public class PropertyCreator implements Creator<Property> {
	private String value;
	private PropertyName propertyName;
	public Property create() {
		return new PropertyImpl(propertyName, value);
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setPropertyName(PropertyName propertyName) {
		this.propertyName = propertyName;
	}
	public PropertyName getPropertyName() {
		return propertyName;
	}

}
