package yogi.property;

public class PropertyImpl implements Property{
	private PropertyName propertyName;
	private String value;
	public PropertyImpl(String className, String name, boolean field, String value) {
		super();
		this.propertyName = new PropertyName(className, name, field);
		this.value = value;
	}
	
	protected PropertyImpl(PropertyName propertyName, String value) {
		super();
		this.propertyName = propertyName;
		this.value = value;
	}

	public String getClassName() {
		return propertyName.getClassName();
	}
	public String getName() {
		return propertyName.getName();
	}
	public String getValue() {
		return value;
	}
	public boolean isField()
	{
		return propertyName.isField();
	}
	
	public PropertyName getPropertyName() {
		return propertyName;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getPropertyName()).append(' ');
		sb.append(getValue());
		return sb.toString();
	}
	
}
