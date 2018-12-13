package yogi.property.management;

import yogi.property.Property;
import yogi.property.PropertyCreator;
import yogi.property.PropertyManager;
import yogi.property.PropertyValidator;
import yogi.property.io.PropertyScanner;

public class PropertiesManager implements PropertiesManagerMBean {
	private PropertyValidator propertyValidator = new PropertyValidator();
	public boolean setProperty(String propertyName, String value) {
		PropertyCreator propertyCreator = PropertyScanner.scan(propertyName, value);
		Property property = propertyCreator.create();
		if(!propertyValidator.validate(property)) return false;
		return PropertyManager.assignProperty(property);
	}
	public String getProperty(String propertyName) {
		Object propertyValue = PropertyManager.getPropertyValue(propertyName);
		if (propertyValue == null) {
			return "null";
		}
		return propertyValue.toString();
	}
}
