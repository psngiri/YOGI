package yogi.base.indexing;

import java.util.HashMap;
import java.util.Map;

import yogi.base.app.ErrorReporter;

public class UniqueMultiIndexer<V> extends MultiIndexer<V> {

	public UniqueMultiIndexer(int numberOfKeys) {
		super(numberOfKeys);
	}

	@Override
	public V index(V value, Object... keys) {
		V oldValue = super.index(value, keys);
		if(oldValue != null)
		{
			Map<String, Object> error = new HashMap<String, Object>(3);
			error.put("Keys:", keys);
			error.put("Old Value:", oldValue);
			error.put("New Value:", value);
			ErrorReporter.get().error("Unique Constraint Violated", error);
		}
		return oldValue;
	}

}
