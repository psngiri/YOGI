package yogi.server.gui.user.preferences.level.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.server.gui.user.preferences.UserPreferencesData;
import yogi.server.gui.user.preferences.level.UserPreferencesLevel;


public class UserPreferencesLevelTest extends TestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	protected void tearDown() throws Exception {
		TestErrorReporter.end();
		Factory.clearAllFactories();
		super.tearDown();
	}

	public void testDomesticAA() {		
		UserPreferencesData user = new UserPreferencesData();
		user.setProperty("color", "blue");
		UserPreferencesData aad = new UserPreferencesData();
		aad.setProperty("col1", "aadom1");
		aad.setProperty("col2", "aadom2");
		UserPreferencesData aaa = new UserPreferencesData();
		aaa.setProperty("col1", "aa1");
		aaa.setProperty("col2", "aa2");
		UserPreferencesData system = null;
		
		List<UserPreferencesData> updList = new ArrayList<UserPreferencesData>();
		updList.add(user);
		updList.add(aad);
		updList.add(aaa);
		updList.add(system);
		UserPreferencesLevel upl = new UserPreferencesLevel(updList);
		
		assertEquals("blue", upl.getProperty("color"));
		assertEquals("aadom1", upl.getProperty("col1"));
		assertEquals("aadom2", upl.getProperty("col2"));
	}

	public void testInternationalAA() {		
		UserPreferencesData user = null;
		UserPreferencesData aai = new UserPreferencesData();
		aai.setProperty("col1", "aaint1");
		aai.setProperty("col2", "aaint2");
		UserPreferencesData aaa = new UserPreferencesData();
		aaa.setProperty("col1", "aa1");
		aaa.setProperty("col2", "aa2");
		UserPreferencesData system = new UserPreferencesData();
		
		List<UserPreferencesData> updList = new ArrayList<UserPreferencesData>();
		updList.add(user);
		updList.add(aai);
		updList.add(aaa);
		updList.add(system);
		UserPreferencesLevel upl = new UserPreferencesLevel(updList);
		
		assertEquals("aaint1", upl.getProperty("col1"));
		assertEquals("aaint2", upl.getProperty("col2"));
	}

	public void testAnyAA() {		
		UserPreferencesData user = new UserPreferencesData();
		UserPreferencesData aaa = new UserPreferencesData();
		aaa.setProperty("col1", "aa1");
		aaa.setProperty("col2", "aa2");
		UserPreferencesData system = new UserPreferencesData();
		
		List<UserPreferencesData> updList = new ArrayList<UserPreferencesData>();
		updList.add(user);
		updList.add(aaa);
		updList.add(system);
		UserPreferencesLevel upl = new UserPreferencesLevel(updList);
		
		assertEquals("aa1", upl.getProperty("col1"));
				
	}

	public void testAnyBA() {		
		UserPreferencesData user = new UserPreferencesData();
		UserPreferencesData baa = new UserPreferencesData();
		baa.setProperty("col1", "ba1");
		baa.setProperty("col2", "ba2");
		UserPreferencesData system = new UserPreferencesData();
		system.setProperty("col1", "1");
		system.setProperty("col2", "2");
		system.setProperty("name", "value");
				
		List<UserPreferencesData> updList = new ArrayList<UserPreferencesData>();
		updList.add(user);
		updList.add(baa);
		updList.add(system);
		UserPreferencesLevel upl = new UserPreferencesLevel(updList);
		
		assertEquals("ba1", upl.getProperty("col1"));
		assertEquals("ba2", upl.getProperty("col2"));
		assertEquals("value", upl.getProperty("name"));
	}

	public void testSystem() {
		UserPreferencesData user = new UserPreferencesData();
		UserPreferencesData system = new UserPreferencesData();
		system.setProperty("col1", "1");
		system.setProperty("col2", "2");
		system.setProperty("name", "value");
		
		List<UserPreferencesData> updList = new ArrayList<UserPreferencesData>();
		updList.add(user);
		updList.add(null);
		updList.add(system);
		UserPreferencesLevel upl = new UserPreferencesLevel(updList);
		
		assertEquals("1", upl.getProperty("col1"));
		assertEquals("2", upl.getProperty("col2"));
		assertEquals("value", upl.getProperty("name"));
		assertNull(upl.getProperty("doesntexist"));
		assertNull(upl.getProperty("doesntexist"));
	}
	
	public void testWildCards() {
		UserPreferencesData user = new UserPreferencesData();
		UserPreferencesData aaa = new UserPreferencesData();
		aaa.setProperty("col1", "aa1");
		aaa.setProperty("col2", "aa2");
		aaa.setProperty("QueryTool.*.col3", "aa3");
		aaa.setProperty("*.MarketAttribute.col4", "aa4");
		aaa.setProperty("*.*.col5", "aa5");
        
		UserPreferencesData system = new UserPreferencesData();
		
		List<UserPreferencesData> updList = new ArrayList<UserPreferencesData>();
		updList.add(user);
		updList.add(aaa);
		updList.add(system);
		UserPreferencesLevel upl = new UserPreferencesLevel(updList);
		
		assertEquals("aa1", upl.getProperty("col1"));
		assertEquals("aa2", upl.getProperty("col2"));
		assertEquals("aa3", upl.getProperty("QueryTool.xyz.col3"));
		assertEquals("aa3", upl.getProperty("QueryTool.abc.col3"));
		assertNull(upl.getProperty("Strategy.xyz.col3"));
		assertEquals("aa4", upl.getProperty("QueryTool.MarketAttribute.col4"));
		assertEquals("aa4", upl.getProperty("Strategy.MarketAttribute.col4"));
		assertNull(upl.getProperty("MarketAttribute.col4"));
		assertNull(upl.getProperty("QueryTool.MarketAttribute1.col4"));
		assertEquals("aa5", upl.getProperty("QueryTool.DomesticFareCumulative.col5"));
		assertEquals("aa5", upl.getProperty("QueryTool.InternationalFareCumulative.col5"));
		assertEquals("aa5", upl.getProperty("QueryTool.MarketAttribute.col5"));
		assertEquals("aa5", upl.getProperty("Strategy.Scope.col5"));
		assertNull(upl.getProperty("QueryTool.col5"));
		assertNull(upl.getProperty("col5"));
		
	}
	
}