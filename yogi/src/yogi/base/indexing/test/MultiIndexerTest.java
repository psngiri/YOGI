package yogi.base.indexing.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import yogi.base.indexing.MultiIndexer;
import yogi.base.indexing.MultiIndexMap.Entry;

public class MultiIndexerTest extends TestCase {

	public void test()
	{
		MultiIndexer<String> multiIndexer = new MultiIndexer<String>(5);
		multiIndexer.index("Marker", "com", "aa", "cp", "base", "relationsip");
		assertEquals("Marker", multiIndexer.get("com", "aa", "cp", "base", "relationsip"));
		multiIndexer.index("Relationship", "com", "aa", "cp", "base", "relationsip");
		assertEquals("Relationship", multiIndexer.get("com", "aa", "cp", "base", "relationsip"));
		multiIndexer.index("ObjectReader", "com", "aa", "cp", "base", "io");
		assertEquals("Relationship", multiIndexer.get("com", "aa", "cp", "base", "relationsip"));
		assertEquals("ObjectReader", multiIndexer.get("com", "aa", "cp", "base", "io"));
		List<String> all = multiIndexer.getAll("com", "aa", "cp", "base", "io");
		assertEquals(1, all.size());
		assertEquals("ObjectReader", all.get(0));
		all = multiIndexer.getAll("com", "aa", "cp");
		assertEquals(2, all.size());
		all = multiIndexer.getAll("com", "aa", "c1");
		assertEquals(0, all.size());
		try {
			all = multiIndexer.getAll("com", "aa", "cp", "base", "io", "hich");
			fail("exception expected");
		} catch (RuntimeException e1) {
		}
		
		assertTrue(multiIndexer.contains("com"));
		assertTrue(multiIndexer.contains("com","aa"));
		assertTrue(multiIndexer.contains("com","aa","cp"));
		assertFalse(multiIndexer.contains("com","aa","cp","vik"));
	
		multiIndexer.remove("com", "aa", "cp", "base", "relationsip");
		assertEquals(null, multiIndexer.get("com", "aa", "cp", "base", "relationsip"));
		assertEquals("ObjectReader", multiIndexer.get("com", "aa", "cp", "base", "io"));
		multiIndexer.remove("com", "aa", "cp", "base", "io");
		assertEquals(null, multiIndexer.get("com", "aa", "cp", "base", "io"));
		assertEquals(null, multiIndexer.get("com", "aa", "cp", "base", "indexing"));
		try {
			multiIndexer.get("com", "aa", "cp", "base");
			fail("exception expected");
		} catch (RuntimeException e) {
		}
	}
	public void testGetMatches()
	{
		MultiIndexer<String> multiIndexer = new MultiIndexer<String>(5);
		multiIndexer.index("Marker", 		"com", "ba", "cp", "base", 	"relationship");
		multiIndexer.index("ObjectReader", 	"com", "aa", "cp", "cba", 	"io");
		multiIndexer.index("Factory", 		"com", "ba", "cp", "base", 	"io");
		multiIndexer.index("Indexer", 		"con", "aa", "cp", "abc", 	"indexing");
		ArrayList<String> keys1 = new ArrayList<String>();
		keys1.add("com");
		keys1.add("con");
		ArrayList<String> keys2 = new ArrayList<String>();
		keys2.add("aa");
		keys2.add("ba");
		ArrayList<String> keys3 = new ArrayList<String>();
		keys3.add("cp");
		ArrayList<String> keys4 = new ArrayList<String>();
		keys4.add("base");
		keys4.add("abc");
		ArrayList<String> keys5 = new ArrayList<String>();
		keys5.add("io");
		keys5.add("relationship");
		keys5.add("indexing");
		List<String> list = multiIndexer.getMatches(keys1, keys2, keys3, keys4, keys5);
		assertEquals(3, list.size());
		assertEquals("Factory", list.get(0));
		assertEquals("Marker", list.get(1));
		assertEquals("Indexer", list.get(2));
	}
	
	public void testGetFirstMatch()
	{
		MultiIndexer<String> multiIndexer = new MultiIndexer<String>(5);
		multiIndexer.index("Marker", 		"com", "ba", "cp", "base", 	"relationship");
		multiIndexer.index("ObjectReader", 	"com", "aa", "cp", "cba", 	"io");
		multiIndexer.index("Factory", 		"com", "ba", "cp", "base", 	"io");
		multiIndexer.index("Indexer", 		"con", "aa", "cp", "abc", 	"indexing");
		
		ArrayList<String> keys1 = new ArrayList<String>();
		keys1.add("com");
		keys1.add("con");
		ArrayList<String> keys2 = new ArrayList<String>();
		keys2.add("aa");
		keys2.add("ba");
		ArrayList<String> keys3 = new ArrayList<String>();
		keys3.add("cp");
		ArrayList<String> keys4 = new ArrayList<String>();
		keys4.add("base");
		keys4.add("abc");
		ArrayList<String> keys5 = new ArrayList<String>();
		keys5.add("io");
		keys5.add("relationship");
		keys5.add("indexing");
		String str = multiIndexer.getFirstMatch(keys1, keys2, keys3, keys4, keys5);
		assertEquals("Factory", str);
		
		keys1 = new ArrayList<String>();
		keys1.add("con");
		keys1.add("com");
		keys4 = new ArrayList<String>();
		keys4.add("abc");
		keys4.add("base");
		str = multiIndexer.getFirstMatch(keys1, keys2, keys3, keys4, keys5);
		assertEquals("Indexer", str);
	}
	
	public void testSampleGetFirstMatch()
	{
		MultiIndexer<String> multiIndexer = new MultiIndexer<String>(3);
		multiIndexer.index("B0:0:0", 		"B0", "0", "0");
		multiIndexer.index("B0:0:3", 		"B0", "0", "3");
		multiIndexer.index("B0:0:7", 		"B0", "0", "7");
		multiIndexer.index("B0:0:10", 		"B0", "0", "10");
		multiIndexer.index("B0:30:0", 		"B0", "30", "0");
		multiIndexer.index("B0:60:0", 		"B0", "60", "0");
		
		multiIndexer.index("S1:0:21", 		"S1", "0", "21");
		multiIndexer.index("S1:0:7", 		"S1", "0", "7");
		multiIndexer.index("S1:0:14", 		"S1", "0", "14");
		
		
		ArrayList<String> keys1 = new ArrayList<String>();
		keys1.add("B0");
		ArrayList<String> keys2 = new ArrayList<String>();
		keys2.add("30");
		keys2.add("0");
		ArrayList<String> keys3 = new ArrayList<String>();
		keys3.add("10");
		String str = multiIndexer.getFirstMatch(keys1, keys2, keys3);
		assertEquals("B0:0:10", str);	
/*		
		List<String> list = multiIndexer.getMatches(keys1, keys2, keys3);
		assertEquals(2, list.size());
		assertEquals("B0:60:0", list.get(0));
		assertEquals("B0:30:0", list.get(1));
*/	}
	
	public void testKeySet()
	{
		MultiIndexer<String> multiIndexer = new MultiIndexer<String>(5);
		multiIndexer.index("Marker", "com", "ba", "cp", "base", "relationship");
		multiIndexer.index("ObjectReader", "com", "aa", "cp", "cba", "io");
		multiIndexer.index("Factory", "com", "ba", "cp", "base", "io");
		multiIndexer.index("Indexer", "con", "aa", "cp", "abc", "indexing");
		ArrayList<String> keys1 = new ArrayList<String>();
		keys1.add("com");
		keys1.add("con");
		ArrayList<String> keys2 = new ArrayList<String>();
		keys2.add("aa");
		keys2.add("ba");
		ArrayList<String> keys3 = new ArrayList<String>();
		keys3.add("cp");
		ArrayList<String> keys4 = new ArrayList<String>();
		keys4.add("base");
		keys4.add("abc");
		ArrayList<String> keys5 = new ArrayList<String>();
		keys5.add("io");
		keys5.add("relationship");
		keys5.add("indexing");
		Set<?> keySet = multiIndexer.keySet();
		assertEquals(2, keySet.size());
		assertTrue(keySet.contains("com"));
		assertTrue(keySet.contains("con"));
		keySet = multiIndexer.keySet(1);
		assertEquals(2, keySet.size());
		assertTrue(keySet.contains("ba"));
		assertTrue(keySet.contains("aa"));	
		keySet = multiIndexer.keySet(2);
		assertEquals(1, keySet.size());
		assertTrue(keySet.contains("cp"));
		keySet = multiIndexer.keySet(3);
		assertEquals(3, keySet.size());
		assertTrue(keySet.contains("base"));
		assertTrue(keySet.contains("cba"));	
		assertTrue(keySet.contains("abc"));
		keySet = multiIndexer.keySet(4);
		assertEquals(3, keySet.size());
		assertTrue(keySet.contains("relationship"));
		assertTrue(keySet.contains("io"));	
		assertTrue(keySet.contains("indexing"));	
		try {
			keySet = multiIndexer.keySet(5);
			fail("Exception expected");
		} catch (RuntimeException e) {
		}
	}

	public void testEntrySet()
	{
		MultiIndexer<String> multiIndexer = new MultiIndexer<String>(5);
		multiIndexer.index("Marker", "com", "ba", "cp", "base", "relationship");
		multiIndexer.index("ObjectReader", "com", "aa", "cp", "cba", "io");
		multiIndexer.index("Factory", "com", "ba", "cp", "base", "io");
		multiIndexer.index("Indexer", "con", "aa", "cp", "abc", "indexing");
		Set<Entry<String>> entrySet = multiIndexer.entrySet();
		assertEquals(4, entrySet.size());
		Set<String> checkedValues = new HashSet<String>();
		for(Entry<String> entry: entrySet)
		{
			if(assertEntry(entry, "Marker", "com", "ba", "cp", "base", "relationship")) checkedValues.add("Marker");
			if(assertEntry(entry, "ObjectReader", "com", "aa", "cp", "cba", "io")) checkedValues.add("ObjectReader");
			if(assertEntry(entry, "Factory", "com", "ba", "cp", "base", "io")) checkedValues.add("Factory");
			if(assertEntry(entry, "Indexer", "con", "aa", "cp", "abc", "indexing")) checkedValues.add("Indexer");
		}
		assertTrue(checkedValues.contains("Marker"));
		assertTrue(checkedValues.contains("ObjectReader"));
		assertTrue(checkedValues.contains("Factory"));
		assertTrue(checkedValues.contains("Indexer"));
	}
	
	private boolean assertEntry(Entry<String> entry, Object value, Object... keys)
	{
		Object[] entryKeys = entry.getKeys();
		if(entry.getValue().equals(value))
		{
			for(int i = 0; i < keys.length; i ++) 
				assertEquals(keys[i], entryKeys[i]);
			return true;
		}
		return false;
	}
	
}
