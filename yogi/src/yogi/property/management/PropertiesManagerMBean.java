package yogi.property.management;

public interface PropertiesManagerMBean {
	boolean setProperty(String propertyName, String value);
	String getProperty(String propertyName);
}
