package yogi.base.indexing;

import java.util.HashMap;
import java.util.Map;

import yogi.base.app.ErrorReporter;


public class UniqueIndexer<K, V> extends Indexer<K, V> {
	@Override
	public V index(K key, V value) {
		V oldValue = super.index(key, value);
		if(oldValue != null)
		{
			Map<String, Object> error = new HashMap<String, Object>(3);
			error.put("Key:", key);
			error.put("Old Value:", oldValue);
			error.put("New Value:", value);
			ErrorReporter.get().error("Unique Constraint Violated", error);
		}
		return oldValue;
	}

}
