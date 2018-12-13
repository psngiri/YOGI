package yogi.base.indexing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



public class Indexer<K, V>  implements yogi.base.indexing.IndexMap<K, V, V>{
	private Map<K, V> map;

	public Indexer() {
		super();
		map = new LinkedHashMap<K, V>();
	}

	public Indexer(int capacity) {
		super();
		map = new LinkedHashMap<K, V>(capacity);
	}
	
	public V index(K key, V value) {
		return map.put(key, value);
	}

	public V remove(K key) {
		return map.remove(key);
	}

	public V get(K key) {
		return map.get(key);
	}
	
	public boolean contains(K key){
		return map.containsKey(key);
	}
	
	public List<V> getMatches(K[] keys)
	{
		return getMatches(Arrays.asList(keys));
	}
	
	public List<V> getMatches(Collection<K> keys)
	{
		if(keys.isEmpty()) return new ArrayList<V>(values());
		List<V> rtnValue = new ArrayList<V>();
		for(K key: keys)
		{
			V value = map.get(key);
			if(value != null) rtnValue.add(value);
		}
		return rtnValue;
	}

	public V getFirstMatch(Collection<K> keys)
	{
		if(keys.isEmpty()){
			Collection<V> values = values();
			if(values.isEmpty()) return null;
			return values.iterator().next();
		}
		for(K key: keys)
		{
			V value = map.get(key);
			if(value != null) return value;
		}
		return null;
	}

	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> entrySet = map.entrySet();
		return entrySet;
	}

	public Collection<V> values() {
		return map.values();
	}

	@Override
	public String toString() {
		return map.toString();
	}

	public void clear() {
		map.clear();
	}

	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Set<K> keySet() {
		return map.keySet();
	}

}
