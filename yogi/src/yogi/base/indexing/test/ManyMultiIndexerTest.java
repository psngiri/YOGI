package yogi.base.indexing.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import yogi.base.indexing.ManyMultiIndexer;
import yogi.base.indexing.MultiIndexMap.Entry;

public class ManyMultiIndexerTest extends TestCase {
	public void test()
	{
		ManyMultiIndexer<String> manyMultiIndexer = new ManyMultiIndexer<String>(5);
		manyMultiIndexer.index("Marker", "com", "aa", "cp", "base", "relationsip");
		List<String> list = manyMultiIndexer.get("com", "aa", "cp", "base", "relationsip");
		assertEquals(1, list.size());
		assertEquals("Marker", list.get(0));
		manyMultiIndexer.index("Relationship", "com", "aa", "cp", "base", "relationsip");
		list = manyMultiIndexer.get("com", "aa", "cp", "base", "relationsip");
		assertEquals(2, list.size());
		assertEquals("Marker", list.get(0));
		assertEquals("Relationship", list.get(1));
		manyMultiIndexer.index("ObjectReader", "com", "aa", "cp", "base", "io");
		list = manyMultiIndexer.get("com", "aa", "cp", "base", "relationsip");
		assertEquals(2, list.size());
		assertEquals("Marker", list.get(0));
		assertEquals("Relationship", list.get(1));
		list = manyMultiIndexer.get("com", "aa", "cp", "base", "io");
		assertEquals(1, list.size());
		assertEquals("ObjectReader", list.get(0));
		List<String> all = manyMultiIndexer.getAll("com", "aa", "cp", "base", "io");
		assertEquals(1, all.size());
		assertEquals("ObjectReader", all.get(0));
		all = manyMultiIndexer.getAll("com", "aa", "cp");
		assertEquals(3, all.size());
		all = manyMultiIndexer.getAll("com", "aa", "c1");
		assertEquals(0, all.size());
		try {
			all = manyMultiIndexer.getAll("com", "aa", "cp", "base", "io", "hich");
			fail("exception expected");
		} catch (RuntimeException e1) {
		}
		
		assertTrue(manyMultiIndexer.contains("com"));
		assertTrue(manyMultiIndexer.contains("com","aa"));
		assertTrue(manyMultiIndexer.contains("com","aa","cp","base", "relationsip"));
		assertFalse(manyMultiIndexer.contains("com","aa","cp","vik"));
		
		String marker = manyMultiIndexer.removeItem("Marker", "com", "aa", "cp", "base", "relationsip");
		assertEquals("Marker", marker);
		list = manyMultiIndexer.get("com", "aa", "cp", "base", "relationsip");
		assertEquals(1, list.size());
		assertEquals("Relationship", list.get(0));
		List<String> items = manyMultiIndexer.remove("com", "aa", "cp", "base", "relationsip");
		assertEquals(1, items.size());
		list = manyMultiIndexer.get("com", "aa", "cp", "base", "relationsip");
		assertEquals(0, list.size());
		list = manyMultiIndexer.get("com", "aa", "cp", "base", "io");
		assertEquals(1, list.size());
		assertEquals("ObjectReader", list.get(0));
		items = manyMultiIndexer.remove("com", "aa", "cp", "base", "io");
		assertEquals(1, items.size());
		list = manyMultiIndexer.get("com", "aa", "cp", "base", "io");
		assertEquals(0, list.size());
		list = manyMultiIndexer.get("com", "aa", "cp", "base", "indexing");
		assertEquals(0, list.size());
		
		try {
			manyMultiIndexer.get("com", "aa", "cp", "base");
			fail("exception expected");
		} catch (RuntimeException e) {
		}
	}
	
	public void testGetMatches()
	{
		ManyMultiIndexer<String> manyMultiIndexer = new ManyMultiIndexer<String>(5);
		manyMultiIndexer.index("Marker", "com", "ba", "cp", "base", "relationship");
		manyMultiIndexer.index("ObjectReader", "com", "aa", "cp", "cba", "io");
		manyMultiIndexer.index("Factory", "com", "ba", "cp", "base", "io");
		manyMultiIndexer.index("Indexer", "con", "aa", "cp", "abc", "indexing");
		manyMultiIndexer.index("Marker1", "com", "ba", "cp", "base", "relationship");
		manyMultiIndexer.index("ObjectReader1", "com", "aa", "cp", "cba", "io");
		manyMultiIndexer.index("Factory", "com", "ba", "cp", "base", "io");
		manyMultiIndexer.index("Indexer1", "con", "aa", "cp", "abc", "indexing");
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
		List<String> list = manyMultiIndexer.getMatches(keys1, keys2, keys3, keys4, keys5);
		assertEquals(6, list.size());
		assertEquals("Factory", list.get(0));
		assertEquals("Factory", list.get(1));
		assertEquals("Marker", list.get(2));
		assertEquals("Marker1", list.get(3));
		assertEquals("Indexer", list.get(4));
		assertEquals("Indexer1", list.get(5));
	}

	public void testEntrySet()
	{
		ManyMultiIndexer<String> manyMultiIndexer = new ManyMultiIndexer<String>(5);
		manyMultiIndexer.index("Marker", "com", "ba", "cp", "base", "relationship");
		manyMultiIndexer.index("ObjectReader", "com", "aa", "cp", "cba", "io");
		manyMultiIndexer.index("Factory", "com", "ba", "cp", "base", "io");
		manyMultiIndexer.index("Indexer", "con", "aa", "cp", "abc", "indexing");
		manyMultiIndexer.index("Marker1", "com", "ba", "cp", "base", "relationship");
		manyMultiIndexer.index("ObjectReader1", "com", "aa", "cp", "cba", "io");
		manyMultiIndexer.index("Factory1", "com", "ba", "cp", "base", "io");
		manyMultiIndexer.index("Indexer1", "con", "aa", "cp", "abc", "indexing");
		Set<Entry<List<String>>> entrySet = manyMultiIndexer.entrySet();
		assertEquals(4, entrySet.size());
		for(Entry<List<String>> entry: entrySet)
		{
			List<String> value = assertEntry(entry, "com", "ba", "cp", "base", "relationship");
			if(value != null)
			{
				assertTrue(value.contains("Marker"));
				assertTrue(value.contains("Marker1"));
			}
			value = assertEntry(entry, "com", "aa", "cp", "cba", "io");
			if(value != null)
			{
				assertTrue(value.contains("ObjectReader"));
				assertTrue(value.contains("ObjectReader1"));
			}
			value = assertEntry(entry, "com", "ba", "cp", "base", "io");
			if(value != null)
			{
				assertTrue(value.contains("Factory"));
				assertTrue(value.contains("Factory1"));
			}
			value = assertEntry(entry, "con", "aa", "cp", "abc", "indexing");
			if(value != null)
			{
				assertTrue(value.contains("Indexer"));
				assertTrue(value.contains("Indexer1"));
			}
		}
	}
	
	public void testEntrySet1()
	{
		ManyMultiIndexer<String> manyMultiIndexer = new ManyMultiIndexer<String>(5);
		Object[] keys = new Object[]{"com", "ba", "cp", "base", "relationship"};
		manyMultiIndexer.index("Marker", keys);
		keys = new Object[]{"com", "aa", "cp", "cba", "io"};
		manyMultiIndexer.index("ObjectReader", keys);
		keys = new Object[]{"com", "ba", "cp", "base", "io"};
		manyMultiIndexer.index("Factory", keys);
		manyMultiIndexer.index("Indexer", "con", "aa", "cp", "abc", "indexing");
		keys = new Object[]{"com", "ba", "cp", "base", "relationship"};
		manyMultiIndexer.index("Marker1", keys);
		keys = new Object[]{"com", "aa", "cp", "cba", "io"};
		manyMultiIndexer.index("ObjectReader1", keys);
		keys = new Object[]{"com", "ba", "cp", "base", "io"};
		manyMultiIndexer.index("Factory1", keys);
		keys = new Object[]{"con", "aa", "cp", "abc", "indexing"};
		manyMultiIndexer.index("Indexer1", keys);
		Set<Entry<List<String>>> entrySet = manyMultiIndexer.entrySet();
//		assertEquals(4, entrySet.size());
		for(Entry<List<String>> entry: entrySet)
		{
			List<String> value = assertEntry(entry, "com", "ba", "cp", "base", "relationship");
			if(value != null)
			{
				assertTrue(value.contains("Marker"));
				assertTrue(value.contains("Marker1"));
			}
			value = assertEntry(entry, "com", "aa", "cp", "cba", "io");
			if(value != null)
			{
				assertTrue(value.contains("ObjectReader"));
				assertTrue(value.contains("ObjectReader1"));
			}
			value = assertEntry(entry, "com", "ba", "cp", "base", "io");
			if(value != null)
			{
				assertTrue(value.contains("Factory"));
				assertTrue(value.contains("Factory1"));
			}
			value = assertEntry(entry, "con", "aa", "cp", "abc", "indexing");
			if(value != null)
			{
				assertTrue(value.contains("Indexer"));
				assertTrue(value.contains("Indexer1"));
			}
		}
	}
	public void testEntrySet1Key()
	{
		ManyMultiIndexer<String> manyMultiIndexer = new ManyMultiIndexer<String>(1);
		Object[] keys = new Object[]{"com"};
		manyMultiIndexer.index("Marker", keys);
		keys = new Object[]{"com"};
		manyMultiIndexer.index("ObjectReader", keys);
		keys = new Object[]{"com"};
		manyMultiIndexer.index("Factory", keys);
		manyMultiIndexer.index("Indexer", "con");
		keys = new Object[]{"ba"};
		manyMultiIndexer.index("Marker1", keys);
		keys = new Object[]{"aa"};
		manyMultiIndexer.index("ObjectReader1", keys);
		keys = new Object[]{"com"};
		manyMultiIndexer.index("Factory1", keys);
		keys = new Object[]{"con"};
		manyMultiIndexer.index("Indexer1", keys);
		Set<Entry<List<String>>> entrySet = manyMultiIndexer.entrySet();
//		assertEquals(4, entrySet.size());
		for(Entry<List<String>> entry: entrySet)
		{
			List<String> value = assertEntry(entry, "com");
			if(value != null)
			{
				assertTrue(value.contains("Marker"));
				assertTrue(value.contains("ObjectReader"));
				assertTrue(value.contains("Factory"));
				assertTrue(value.contains("Factory1"));
			}
			value = assertEntry(entry, "aa");
			if(value != null)
			{
				assertTrue(value.contains("ObjectReader1"));
			}
			value = assertEntry(entry, "ba");
			if(value != null)
			{
				assertTrue(value.contains("Marker1"));
			}
			value = assertEntry(entry, "con");
			if(value != null)
			{
				assertTrue(value.contains("Indexer"));
				assertTrue(value.contains("Indexer1"));
			}
		}
	}
	
	private List<String> assertEntry(Entry<List<String>> entry, Object... keys)
	{
		Object[] entryKeys = entry.getKeys();
			for(int i = 0; i < keys.length; i ++) 
				if(!keys[i].equals(entryKeys[i])) return null;
		return entry.getValue();
	}
}
