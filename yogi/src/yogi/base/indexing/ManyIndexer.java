package yogi.base.indexing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import yogi.base.util.immutable.ImmutableList;


public class ManyIndexer<K, V> implements IndexMap<K, V, List<V>>{
	private IndexMap<K, List<V>, List<V>> indexMap;
	private static ImmutableList<Object> emptyList = new ImmutableList<Object>(new ArrayList<Object>(0));

	public ManyIndexer() {
		super();
		indexMap = new Indexer<K, List<V>>();
	}

	public ManyIndexer(int capacity) {
		super();
		indexMap = new Indexer<K, List<V>>(capacity);
	}
	
	public List<V> index(K key, V value) {
		List<V> items = muttableGet(key);
		if(items == null)
		{
			items = new ArrayList<V>();
			indexMap.index(key, items);
		}
		items.add(value);
		return items;
	}

	public List<V> index(K key, List<V> values) {
		List<V> items = muttableGet(key);
		if(items == null)
		{
			items = new ArrayList<V>();
			indexMap.index(key, items);
		}
		items.addAll(values);
		return items;
	}
	
	public List<V> remove(K key) {
		return indexMap.remove(key);
	}

	protected List<V> muttableGet(K key)
	{
		return indexMap.get(key);
	}
	
	public ImmutableList<V> get(K key) {
		List<V> list = muttableGet(key);
		if(list != null) return new ImmutableList<V>(list);
		return new ImmutableList<V>(new ArrayList<V>(0));
	}
	
	public boolean contains(K key) {
		List<V> list = muttableGet(key);
		return list == null ? false : !list.isEmpty();
	}
	

	public List<V> getMatches(K[] keys)
	{
		return getMatches(Arrays.asList(keys));
	}
	
	public List<V> getMatches(Collection<K> keys)
	{
		if(keys.isEmpty()) keys = keySet();
		List<V> rtnValue = new ArrayList<V>();
		for(K key: keys)
		{
			List<V> items = muttableGet(key);
			if(items != null) rtnValue.addAll(items);
		}
		return rtnValue;
	}

	@SuppressWarnings("unchecked")
	public List<V> getFirstMatch(Collection<K> keys)
	{
		if(keys.isEmpty()) keys = keySet();
		for(K key: keys)
		{
			List<V> items = muttableGet(key);
			if(items != null) return items;
		}
		return (List<V>) emptyList;
	}
	
	public V removeItem(K key, V value) {
		List<V> items = muttableGet(key);
		if(items == null)
		{
			return null;
		}
		boolean success = items.remove(value);
		if (success && items.isEmpty())
		{
			remove(key);
		}
		return (success)? value : null;
	}
	
	public Set<Entry<K, List<V>>> entrySet() {
		Set<Entry<K, List<V>>> entrySet = indexMap.entrySet();
		return entrySet;
	}

	@Override
	public String toString() {
		return indexMap.toString();
	}

	public Collection<V> values() {
		Collection<V> rtnValue = new ArrayList<V>();
		for(Collection<V> item: indexMap.values())
		{
			rtnValue.addAll(item);
		}
		return rtnValue;
	}

	public void clear() {
		indexMap.clear();
	}

	public int size() {
		return indexMap.size();
	}

	public boolean isEmpty() {
		return indexMap.isEmpty();
	}

	public Set<K> keySet() {
		return indexMap.keySet();
	}
}
