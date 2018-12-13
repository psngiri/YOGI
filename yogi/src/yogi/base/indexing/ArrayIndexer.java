package yogi.base.indexing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



public class ArrayIndexer<V>  implements yogi.base.indexing.IndexMap<Integer, V, V>{
	private V[] list;

	@SuppressWarnings("unchecked")
	public ArrayIndexer(int capacity) {
		super();
		list = (V[]) new Object[capacity];
	}
	
	public V index(Integer key, V value) {
		validateKey(key);
		return list[key] = value;
	}

	private void validateKey(int key) {
		if(key >= list.length) throw new RuntimeException("key should be less than the capacity:" + list.length);
	}

	public V remove(Integer key) {
		validateKey(key);
		return list[key] = null;
	}

	public V get(Integer key) {
		validateKey(key);
		return list[key];
	}
	
	public boolean contains(Integer key){
		return key < list.length;
	}
	
	public List<V> getMatches(Integer[] keys)
	{
		List<V> rtnValue = new ArrayList<V>(keys.length);
		for(int key:keys){
			validateKey(key);
			rtnValue.add(list[key]);
		}
		return rtnValue;
	}
	
	public List<V> getMatches(Collection<Integer> keys)
	{
		List<V> rtnValue = new ArrayList<V>(keys.size());
		for(int key:keys){
			validateKey(key);
			rtnValue.add(list[key]);
		}
		return rtnValue;
	}

	public V getFirstMatch(Collection<Integer> keys)
	{
		for(int key: keys)
		{
			V value = list[key];
			if(value != null) return value;
		}
		return null;
	}

	public Set<Entry<Integer, V>> entrySet() {
		Set<Entry<Integer, V>> entrySet = new HashSet<Entry<Integer, V>>();
		for(int i = 0; i < list.length; i ++){
			if(list[i] != null) entrySet.add(new MyEntry<Integer, V>(i, list[i]));
		}
		return entrySet;
	}

	public Collection<V> values() {
		List<V> rtnValue = new ArrayList<V>();
		for(int i = 0; i < list.length; i ++){
			if(list[i] != null) rtnValue.add(list[i]);
		}
		return rtnValue;
	}

	@Override
	public String toString() {
		return list.toString();
	}

	public void clear() {
		for(int i = 0; i < list.length; i ++){
			list[i]=null;
		}
	}

	public int size() {
		return values().size();
	}

	public boolean isEmpty() {
		return values().isEmpty();
	}

	public Set<Integer> keySet() {
		Set<Integer> rtnValue = new HashSet<Integer>();
		for(int i = 0; i < list.length; i ++){
			if(list[i] != null) rtnValue.add(i);
		}
		return rtnValue;
	}

	static class MyEntry<K, V> implements Map.Entry<K,V>{
		private K key;
		private V value;
		
		public MyEntry(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			throw new RuntimeException("Immutable can't set value");
		}
		
	}
}

