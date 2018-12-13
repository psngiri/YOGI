package yogi.property.test;

import junit.framework.TestCase;

import yogi.base.Creator;
import yogi.property.Property;
import yogi.property.PropertyCreator;
import yogi.property.io.PropertyScanner;

public class PropertyScannerTest extends TestCase {
	public static String stringProperty = "20";
	public static String stringProperty1 = "2";
	public static int stringProperty2 = 0;
	
	public static int getStringProperty3()
	{
		return 3;
	}
	
	public void testScanField()
	{
		PropertyScanner propertyScanner = new PropertyScanner();
		PropertyCreator creator = (PropertyCreator) propertyScanner.scan("test.MainTest:value=20");
		assertEquals("test.MainTest", creator.getPropertyName().getClassName());
		assertEquals("value", creator.getPropertyName().getName());
		assertEquals(true, creator.getPropertyName().isField());
		assertEquals("20", creator.getValue());
	}
	public void testScanField1()
	{
		PropertyScanner propertyScanner = new PropertyScanner();
		PropertyCreator creator = (PropertyCreator) propertyScanner.scan("test.MainTest:value=${yogi.property.test.PropertyScannerTest:stringProperty}");
		assertEquals("test.MainTest", creator.getPropertyName().getClassName());
		assertEquals("value", creator.getPropertyName().getName());
		assertEquals(true, creator.getPropertyName().isField());
		assertEquals("20", creator.getValue());
	}
	public void testScanField2()
	{
		PropertyScanner propertyScanner = new PropertyScanner();
		PropertyCreator creator = (PropertyCreator) propertyScanner.scan("test.MainTest:value=${yogi.property.test.PropertyScannerTest:stringProperty1}1${yogi.property.test.PropertyScannerTest:stringProperty2}");
		assertEquals("test.MainTest", creator.getPropertyName().getClassName());
		assertEquals("value", creator.getPropertyName().getName());
		assertEquals(true, creator.getPropertyName().isField());
		assertEquals("210", creator.getValue());
	}
	public void testScanField3()
	{
		PropertyScanner propertyScanner = new PropertyScanner();
		PropertyCreator creator = (PropertyCreator) propertyScanner.scan("test.MainTest:value=${yogi.property.test.PropertyScannerTest:stringProperty1}1${yogi.property.test.PropertyScannerTest;getStringProperty3}");
		assertEquals("test.MainTest", creator.getPropertyName().getClassName());
		assertEquals("value", creator.getPropertyName().getName());
		assertEquals(true, creator.getPropertyName().isField());
		assertEquals("213", creator.getValue());
	}
	public void testScanFieldError()
	{
		PropertyScanner propertyScanner = new PropertyScanner();
		try {
			propertyScanner.scan("test.MainTest:value=${yogi.property.test.PropertyScannerTest:stringProperty");
			fail("Failed to throw an exception for an in correct property specification");
		} catch (RuntimeException e) {
		}
	}
	public void testScanMethod()
	{
		PropertyScanner propertyScanner = new PropertyScanner();
		PropertyCreator creator = (PropertyCreator) propertyScanner.scan("test.MainTest;setValue=20");
		assertEquals("test.MainTest", creator.getPropertyName().getClassName());
		assertEquals("setValue", creator.getPropertyName().getName());
		assertEquals(false, creator.getPropertyName().isField());
		assertEquals("20", creator.getValue());
	}
	public void testScanError()
	{
		PropertyScanner propertyScanner = new PropertyScanner();
		try {
			propertyScanner.scan("test.MainTest#setValue=20");
			fail("Failed to throw an exception for an in correct property specification");
		} catch (RuntimeException e) {
		}
	}
	public void testScanError1()
	{
		PropertyScanner propertyScanner = new PropertyScanner();
		try {
			propertyScanner.scan("test.MainTest;setValue+20");
			fail("Failed to throw an exception for an in correct property specification");
		} catch (RuntimeException e) {
		}
	}
	public void testSystemProperty(){
		PropertyScanner propertyScanner = new PropertyScanner();
		try {
			String getenv = System.getenv("JAVA_HOME");
			Creator<Property> propertCreator = propertyScanner.scan("yogi.property.test.PropertyScannerTest:stringProperty=${JAVA_HOME}");
			Property property = propertCreator.create();
			assertEquals(getenv, property.getValue());
		} catch (RuntimeException e) {
		}
		
	}
}
