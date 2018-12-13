package yogi.property.alias;

import yogi.property.PropertyImpl;
import yogi.property.PropertyName;

public class PropertyAliasImpl extends PropertyImpl implements PropertyAlias{

	protected PropertyAliasImpl(String className, String name, boolean field, String value) {
		super(className, name, field, value);
	}

	protected PropertyAliasImpl(PropertyName propertyName, String value) {
		super(propertyName, value);
	}
	
	public String getAlias() {
		return getValue();
	}

	public String getIndex() {
		return getValue();
	}
}
