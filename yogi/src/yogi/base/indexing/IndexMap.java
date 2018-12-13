package yogi.base.indexing;

import java.util.Collection;
import java.util.Set;
import java.util.Map.Entry;


public interface IndexMap<K, V, T>{
	T index(K key, V value);
	T remove(K key);
	T get(K key);
	Collection<V> getMatches(Collection<K> keys);
	T getFirstMatch(Collection<K> keys);
	Set<Entry<K, T>> entrySet();
	Collection<V>  values();
	void clear();
	int size();
	boolean isEmpty();
	Set<K> keySet();
	boolean contains(K key);
}