package yogi.base.indexing.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.indexing.Indexer;

public class IndexerTest extends TestCase {
	public void test()
	{
		Indexer<String, String> indexer = new Indexer<String, String>();
		indexer.index("relationsip", "Marker");
		indexer.index("io", "ObjectReader");
		indexer.index("index", "Factory");
		indexer.index("indexer", "Indexer");
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("io");
		keys.add("indexer");
		List<String> list = indexer.getMatches(keys);
		assertEquals(2, list.size());
		assertTrue(list.contains("ObjectReader"));
		assertTrue(list.contains("Indexer"));
	}

	public void testIndex()
	{
		Indexer<String, String> indexer = new Indexer<String, String>();
		assertEquals(null, indexer.index("relationsip", "Marker"));
		assertEquals("Marker", indexer.index("relationsip", "Marker1"));
	}
	
	public void testGet()
	{
		Indexer<String, String> indexer = new Indexer<String, String>();
		indexer.index("relationsip", "Marker");
		indexer.index("io", "ObjectReader");
		indexer.index("index", "Factory");
		indexer.index("indexer", "Indexer");
		String value = indexer.get("io");
		assertEquals("ObjectReader", value);
	}
	public void testCapaticy()
	{
		Indexer<String, String> indexer = new Indexer<String, String>(0);
		indexer.index("relationsip", "Marker");
		indexer.index("io", "ObjectReader");
		indexer.index("index", "Factory");
		indexer.index("indexer", "Indexer");
		String value = indexer.get("io");
		assertEquals("ObjectReader", value);
	}
	
	public void testGetNull()
	{
		Indexer<String, String> indexer = new Indexer<String, String>();
		indexer.index("relationsip", "Marker");
		indexer.index("io", "ObjectReader");
		indexer.index("index", "Factory");
		indexer.index(null, "NullString");
		String value = indexer.get(null);
		assertEquals("NullString", value);
	}
	
	public void testGetMatches()
	{
		Indexer<String, String> indexer = new Indexer<String, String>();
		indexer.index("relationship","Marker");
		indexer.index("io", "ObjectReader");
		indexer.index("io", "Factory");
		indexer.index("indexing", "Indexer");
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("io");
		keys.add("relationship");
		List<String> list = indexer.getMatches(keys);
		assertEquals(2, list.size());
		assertTrue(list.contains("Factory"));
		assertTrue(list.contains("Marker"));
	}

	public void testGetMatchesForEmptyList()
	{
		Indexer<String, String> indexer = new Indexer<String, String>();
		indexer.index("relationship","Marker");
		indexer.index("io", "ObjectReader");
		indexer.index("io", "Factory");
		indexer.index("indexing", "Indexer");
		ArrayList<String> keys = new ArrayList<String>();
		List<String> list = indexer.getMatches(keys);
		assertEquals(3, list.size());
		assertTrue(list.contains("Indexer"));
		assertTrue(list.contains("Factory"));
		assertTrue(list.contains("Marker"));
	}
}
