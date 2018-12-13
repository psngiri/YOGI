package yogi.base.indexing;

import java.util.Collection;
import java.util.List;
import java.util.Set;


public interface MultiIndexMap<V, T> {
	int getNumberOfKeys();
	T get(Object... keys);
	Collection<V> getMatches(Collection... keys);
	T index(V value, Object... keys);
	T remove(Object... keys);
	Collection<V> getAll(Object... keys);
	boolean contains(Object... keys);
	void clear();
	Set<Entry<T>> entrySet();
	Set keySet();
	Set keySet(int keyIndex);

	class Entry<T>{
		Object[] keys;
		T value;
		
		public Entry(T value, Object... keys) {
			super();
			this.keys = keys;
			this.value = value;
		}
		
		public Object[] getKeys() {
			return keys;
		}
		
		public T getValue() {
			return value;
		}
	}
}