package yogi.base.util.test;

import java.util.HashMap;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.base.io.resource.ClassPathResource;
import yogi.base.io.resource.SystemResource;
import yogi.base.util.JsonAssistant;
import yogi.base.util.Pair;

import junit.framework.TestCase;

public class JsonAssistantTest  extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		TestErrorReporter.end();
		Factory.clearAllFactories();
		super.tearDown();
		
	}
	
	public void testtoJson()
	{
		Pair<Integer, String> mypair = new Pair<Integer, String>(5, "test");
		String json = JsonAssistant.get().toJson(mypair);
		assertEquals("{\"a\":5,\"b\":\"test\"}", json);
		System.out.println(json);
		mypair = new Pair<Integer, String>(null, null);
		json = JsonAssistant.get().toJson(mypair);
		System.out.println(json);
		assertEquals("{}", json);
		
		HashMap<String, HashMap<String, String>> lupd = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> aad = new HashMap<String, String>();
		aad.put("col1", "daa1");
		aad.put("col2", "daa2");		
		HashMap<String, String> aai = new HashMap<String, String>();
		aai.put("col1", "iaa1");
		aai.put("col2", "iaa2");		
		HashMap<String, String> aaa = new HashMap<String, String>();
		aaa.put("col1", "aa1");
		aaa.put("col2", "aa2");		
		lupd.put("AA/D", aad);
		lupd.put("AA/I", aai);
		lupd.put("AA/", aaa);
		
		HashMap<String, String> baa = new HashMap<String, String>();
		baa.put("col1", "ba1");
		baa.put("col2", "ba2");
		lupd.put("BA/", baa);
		
		String lupdJson = JsonAssistant.get().toJson(lupd);
		System.out.println(lupdJson);
		
	}
	
	@SuppressWarnings("unchecked")
	public void testFromJsonFile()
	{
		SystemResource resource = new ClassPathResource("mypair.txt", this.getClass());
		Pair<Integer, String> mypair = JsonAssistant.get().fromJsonFile(resource , 0, Pair.class);
		System.out.println(mypair.getFirst());
		System.out.println(mypair.getSecond());

		resource = new ClassPathResource("map.txt", this.getClass());
		HashMap<String, HashMap<String, String>> map = JsonAssistant.get().fromJsonFile(resource , 0, HashMap.class);
		System.out.println(map);
				
	}

	@SuppressWarnings("unchecked")
	public void testJsonObject() {
		SystemResource resource = new ClassPathResource("mypair.txt", this.getClass());
		Pair<Integer, String> mypair = JsonAssistant.get().fromJsonFile(resource, 0, Pair.class);
		String dataStr = JsonAssistant.get().toJson(mypair);
		System.out.println("To Json : " + dataStr);
		
		Object obj = JsonAssistant.get().fromJsonFile(resource, 0, Object.class);
		String objStr = JsonAssistant.get().toJson(obj);
		System.out.println("To Json : " + objStr);
		
	}
	
}
