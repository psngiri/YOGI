package yogi.property.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.base.util.JsonAssistant;
import yogi.base.util.Pair;
import yogi.property.PropertyCreator;
import yogi.property.PropertyManager;
import yogi.property.io.PropertyScanner;

public class PropertyManagerTest extends TestCase {
	public static Pair<Float, String> mypair = new Pair<Float, String>(5.0f, "test");
	public static Map<Integer, List<String>> map;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		TestErrorReporter.end();
	}
	public void testSettingGenericProperty()
	{
		Pair<Float, String> mypair = new Pair<Float, String>(6.0f, "test");
		String json = JsonAssistant.get().toJson(mypair);
		PropertyScanner propertyScanner = new PropertyScanner();
		PropertyCreator creator = (PropertyCreator) propertyScanner.scan("yogi.property.test.PropertyManagerTest:mypair=" + json);
		PropertyManager.assignProperty(creator.create());
		assertEquals(mypair.getSecond(), PropertyManagerTest.mypair.getSecond());
		assertEquals(mypair.getFirst(), PropertyManagerTest.mypair.getFirst());
	}
	
	public void testSettingComplexGenericProperty()
	{
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		List<String> items1 = new ArrayList<String>();
		items1.add("A");
		items1.add("B");
		items1.add("C");
		List<String> items2 = new ArrayList<String>();
		items2.add("A1");
		items2.add("B2");
		items2.add("C3");
		map.put(1, items1);
		map.put(2, items2);
		String json = JsonAssistant.get().toJson(map);
		System.out.println(json);
		PropertyScanner propertyScanner = new PropertyScanner();
		PropertyCreator creator = (PropertyCreator) propertyScanner.scan("yogi.property.test.PropertyManagerTest:map=" + json);
		PropertyManager.assignProperty(creator.create());
		Set<Integer> keySet = PropertyManagerTest.map.keySet();
		assertTrue(keySet.contains(1));
		assertTrue(keySet.contains(2));
		assertEquals("C", PropertyManagerTest.map.get(1).get(2));
	}
	
	public void testFilebasedJsonProperty()
	{
		PropertyScanner propertyScanner = new PropertyScanner();
		PropertyCreator creator = (PropertyCreator) propertyScanner.scan("yogi.property.test.PropertyManagerTest:map=" + "*/com/aa/cp/property/test/testJsonFile.txt");
		PropertyManager.assignProperty(creator.create());
		Set<Integer> keySet = PropertyManagerTest.map.keySet();
		assertTrue(keySet.contains(1));
		assertTrue(keySet.contains(2));
		assertEquals("D", PropertyManagerTest.map.get(1).get(2));
	}
	
}
