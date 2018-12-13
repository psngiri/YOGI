package yogi.base.indexing.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.indexing.ManyIndexer;
import yogi.base.util.immutable.ImmutableList;

public class ManyIndexerTest extends TestCase {
	public void test()
	{
		ManyIndexer<String, String> manyIndexer = new ManyIndexer<String, String>();
		manyIndexer.index("relationsip", "Marker");
		List<String> list = manyIndexer.get("relationsip");
		assertEquals(1, list.size());
		assertEquals("Marker", list.get(0));
		manyIndexer.index("relationsip", "Relationship");
		list = manyIndexer.get("relationsip");
		assertEquals(2, list.size());
		assertEquals("Marker", list.get(0));
		assertEquals("Relationship", list.get(1));
		manyIndexer.index("io", "ObjectReader");
		list = manyIndexer.get( "relationsip");
		assertEquals(2, list.size());
		assertEquals("Marker", list.get(0));
		assertEquals("Relationship", list.get(1));
		list = manyIndexer.get( "io");
		assertEquals(1, list.size());
		assertEquals("ObjectReader", list.get(0));
		String marker = manyIndexer.removeItem("relationsip", "Marker");
		assertEquals("Marker", marker);
		list = manyIndexer.get( "relationsip");
		assertEquals(1, list.size());
		assertEquals("Relationship", list.get(0));
		List<String> items = manyIndexer.remove( "relationsip");
		assertEquals(1, items.size());
		list = manyIndexer.get( "relationsip");
		assertEquals(0, list.size());
		list = manyIndexer.get( "io");
		assertEquals(1, list.size());
		assertEquals("ObjectReader", list.get(0));
		items = manyIndexer.remove( "io");
		assertEquals(1, items.size());
		list = manyIndexer.get( "io");
		assertEquals(0, list.size());
		list = manyIndexer.get( "indexing");
		assertEquals(0, list.size());
	}
	
	public void testIndex()
	{
		ManyIndexer<String, String> manyIndexer = new ManyIndexer<String, String>();
		List<String> values = manyIndexer.index("relationsip", "Marker");
		assertEquals(1, values.size());
		assertEquals("Marker", values.get(0));
		values = manyIndexer.index("relationsip", "Marker1");
		assertEquals(2, values.size());
		assertTrue(values.contains("Marker"));
		assertTrue(values.contains("Marker1"));
	}
	
	public void testGetMatches()
	{
		ManyIndexer<String, String> manyIndexer = new ManyIndexer<String, String>();
		manyIndexer.index("relationship","Marker");
		manyIndexer.index("io", "ObjectReader");
		manyIndexer.index("io", "Factory");
		manyIndexer.index("indexing", "Indexer");
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("io");
		keys.add("relationship");
		List<String> list = manyIndexer.getMatches(keys);
		assertEquals(3, list.size());
		assertTrue(list.contains("ObjectReader"));
		assertTrue(list.contains("Factory"));
		assertTrue(list.contains("Marker"));
	}

	public void testGetMatchesForEmptyList()
	{
		ManyIndexer<String, String> manyIndexer = new ManyIndexer<String, String>();
		manyIndexer.index("relationship","Marker");
		manyIndexer.index("io", "ObjectReader");
		manyIndexer.index("io", "Factory");
		manyIndexer.index("indexing", "Indexer");
		ArrayList<String> keys = new ArrayList<String>();
		List<String> list = manyIndexer.getMatches(keys);
		assertEquals(4, list.size());
		assertTrue(list.contains("Indexer"));
		assertTrue(list.contains("ObjectReader"));
		assertTrue(list.contains("Factory"));
		assertTrue(list.contains("Marker"));
	}
	
	public void testRemoveItem()
	{
		ManyIndexer<String, String> manyIndexer = new ManyIndexer<String, String>();
		manyIndexer.index("relationship","Marker");
		manyIndexer.index("io", "ObjectReader");
		manyIndexer.index("io", "Factory");
		manyIndexer.index("indexing", "Indexer");
		ImmutableList<String> items = manyIndexer.get("io");
		assertEquals(3,manyIndexer.entrySet().size());
		assertEquals(3,manyIndexer.keySet().size());
		assertEquals(2,items.size());
		manyIndexer.removeItem("io", "ObjectReader");
		assertEquals(3,manyIndexer.keySet().size());
		assertEquals(3,manyIndexer.entrySet().size());
		assertEquals(1,manyIndexer.get("io").size());
		manyIndexer.removeItem("io", "Factory");
		assertEquals(2,manyIndexer.entrySet().size());
		assertEquals(2,manyIndexer.keySet().size());
		assertEquals(0,manyIndexer.get("io").size());
	}
}
