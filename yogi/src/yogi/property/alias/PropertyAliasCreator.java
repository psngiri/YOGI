package yogi.property.alias;

import yogi.base.Creator;

public class PropertyAliasCreator implements Creator<PropertyAlias> {
	private String className;
	private String name;
	private boolean field;
	private String value;
	public PropertyAlias create() {
		return new PropertyAliasImpl(className, name, field, value);
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getClassName() {
		return className;
	}
	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}
	public boolean isField() {
		return field;
	}
	public void setField(boolean field) {
		this.field = field;
	}

}
