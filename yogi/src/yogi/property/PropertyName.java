package yogi.property;

public class PropertyName {
	private String className;
	private String name;
	private boolean field;
	
	public PropertyName(String className, String name, boolean field) {
		super();
		this.className = className;
		this.name = name;
		this.field = field;
	}
	
	public String getClassName() {
		return className;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isField()
	{
		return field;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClassName()).append(' ');
		sb.append(getName()).append(' ');
		sb.append(isField()).append(' ');
		return sb.toString();
	}

}
