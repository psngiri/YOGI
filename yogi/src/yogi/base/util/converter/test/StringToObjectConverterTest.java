package yogi.base.util.converter.test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import yogi.base.util.JsonAssistant;
import yogi.base.util.Pair;
import yogi.base.util.converter.StringToObjectConverter;
import com.google.gson.reflect.TypeToken;

public class StringToObjectConverterTest extends TestCase {
	public enum AnEnum { X }

	public void testShouldBeAbleToConvertEnums() throws Exception {
		assertSame(AnEnum.X, StringToObjectConverter.convertTo("X", AnEnum.class));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testConverstionUsingJson(){
		Pair mypair = new Pair(5.0, "test");
		String json = JsonAssistant.get().toJson(mypair);
		System.out.println(json);
		Pair convertTo = (Pair) StringToObjectConverter.convertTo(json, mypair.getClass());
		assertEquals(mypair.getSecond(), convertTo.getSecond());
		assertEquals(mypair.getFirst(), convertTo.getFirst());

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
		String json1 = JsonAssistant.get().toJson(map);
		System.out.println(json1);
		Object object = StringToObjectConverter.convertTo(json1, map.getClass());
		Map<String, List<String>> map1 =  (Map<String, List<String>>) object;

		System.out.println(map1.keySet().iterator().next().getClass());
		System.out.println(JsonAssistant.get().toJson(map1));
		
		Integer int1 = 5;
		assertEquals(int1, StringToObjectConverter.convertTo("5", Integer.class));
		
		Type mapType = new TypeToken<Map<Integer, List<String>>>() {}.getType(); 
		String json2 = JsonAssistant.get().toJson(map);
		System.out.println(json2);
		object = StringToObjectConverter.convertTo(json1, map.getClass(), mapType);
		Map<Integer, List<String>> map2 =  (Map<Integer, List<String>>) object;
		System.out.println(map2.keySet().iterator().next().getClass());
	}
	
	public void testConverstionThrowsProperExceptions(){
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		Type mapType = new TypeToken<Map<Integer, List<String>>>() {}.getType(); 
		try {
			StringToObjectConverter.convertTo("F,T", map.getClass(), mapType);;
			assertTrue(false);
		} catch (Exception e) {
			String message = e.getMessage();
			System.out.println(message);
			assertTrue(message.contains("java.lang.NoSuchMethodException")&&message.contains("java.lang.RuntimeException"));
		}
	}
}
