package yogi.server.gui.user.preferences.level.test;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.property.Property;
import yogi.property.PropertyCreator;
import yogi.property.PropertyManager;
import yogi.property.io.PropertyScanner;
import yogi.server.gui.user.preferences.level.UserPreferencesLevelAssistant;


public class UserPreferencesLevelAssistantTestS extends TestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	protected void tearDown() throws Exception {
		TestErrorReporter.end();
		Factory.clearAllFactories();
		super.tearDown();
	}

	/*
	 * Please make sure the file path mentioned below is changed accordingly based on eclipse workspace path 
	 */
	public void testProperyConfigByReadingJsonFromFile1() {
		assertTrue(UserPreferencesLevelAssistant.levelUPD.isEmpty());
		PropertyScanner propertyScanner = new PropertyScanner();
		String propertyNameValue = "";
		propertyNameValue = "yogi.server.gui.user.preferences.level.UserPreferencesLevelAssistant:levelUPD=D:\\Workspace\\JAF\\src\\com\\aa\\cp\\server\\gui\\user\\preferences\\level\\test\\carrier_preferences_data.json";
		//propertyNameValue = "yogi.server.gui.user.preferences.level.UserPreferencesLevelAssistant:levelUPD=data/userpreferences/carrier_preferences_data.json";
		PropertyCreator creator = (PropertyCreator) propertyScanner.scan(propertyNameValue);
		Property property = creator.create();
		PropertyManager.assignProperty(property);
		assertFalse(UserPreferencesLevelAssistant.levelUPD.isEmpty());
		assertEquals("aa1", UserPreferencesLevelAssistant.levelUPD.get("AA/").getProperty("col1"));
		assertEquals("aa2", UserPreferencesLevelAssistant.levelUPD.get("AA/").getProperty("col2"));
		assertEquals("daa1", UserPreferencesLevelAssistant.levelUPD.get("AA/D").getProperty("col1"));
		assertEquals("daa2", UserPreferencesLevelAssistant.levelUPD.get("AA/D").getProperty("col2"));
		assertEquals("iaa1", UserPreferencesLevelAssistant.levelUPD.get("AA/I").getProperty("col1"));
		assertEquals("iaa2", UserPreferencesLevelAssistant.levelUPD.get("AA/I").getProperty("col2"));
		assertEquals("ba1", UserPreferencesLevelAssistant.levelUPD.get("BA/").getProperty("col1"));
		assertEquals("ba2", UserPreferencesLevelAssistant.levelUPD.get("BA/").getProperty("col2"));		
	}
	
	public void testProperyConfigByReadingJsonFromFile2() {
		PropertyScanner propertyScanner = new PropertyScanner();
		String propertyNameValue = "";
		propertyNameValue = "yogi.server.gui.user.preferences.level.UserPreferencesLevelAssistant:system=D:\\Workspace\\JAF\\src\\com\\aa\\cp\\server\\gui\\user\\preferences\\level\\test\\system_preferences_data.json";
		//propertyNameValue = "yogi.server.gui.user.preferences.level.UserPreferencesLevelAssistant:system=data/userpreferences/system_preferences_data.json";
		PropertyCreator creator = (PropertyCreator) propertyScanner.scan(propertyNameValue);
		Property property = creator.create();
		PropertyManager.assignProperty(property);
		assertEquals("NexGen", UserPreferencesLevelAssistant.system.getProperty("title"));
		assertEquals("Welcome", UserPreferencesLevelAssistant.system.getProperty("message"));
		
	}
	
}
