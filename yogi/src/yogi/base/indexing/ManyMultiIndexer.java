package yogi.base.indexing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import yogi.base.util.immutable.ImmutableList;



public class ManyMultiIndexer<V> implements MultiIndexMap<V, List<V>> {
	private IndexMap indexer = new Indexer();
	private int numberOfKeys;

	public ManyMultiIndexer(int numberOfKeys) {
		super();
		this.numberOfKeys = numberOfKeys;
		if(numberOfKeys ==1){
			indexer = new ManyIndexer<Object, V>();
		}
	}

	@SuppressWarnings("unchecked")
	public ImmutableList<V> get(Object... keys) {
		IndexMap myIndexer = MultiIndexHelper.getFinalIndexer(indexer, numberOfKeys, keys);
		if(myIndexer == null) return null;
		return (ImmutableList<V>) myIndexer.get(keys[keys.length - 1]);
	}

	public List<V> getMatches(Collection... keys)
	{
		if(keys.length != numberOfKeys) throw new RuntimeException("Number of Keys : "+ numberOfKeys + " less than keys length: " + keys.length); 
		List<V> rtnValue = new ArrayList<V>();
		MultiIndexHelper.getMatches(rtnValue, indexer, keys);
		return rtnValue;
	}

	public V getFirstMatch(Collection... keys)
	{
		if(keys.length != numberOfKeys) throw new RuntimeException("Number of Keys : "+ numberOfKeys + " less than keys length: " + keys.length); 
		return (V) MultiIndexHelper.getFirstMatch(indexer, keys);
	}

	@SuppressWarnings("unchecked")
	public List<V> index(V value, Object... keys) {
		IndexMap myIndexer = MultiIndexHelper.getIndexer(indexer, true, numberOfKeys-1, true, keys);
		Object lastKey = keys[keys.length - 1];
		return (List<V>) myIndexer.index(lastKey, value);
	}

	@SuppressWarnings("unchecked")
	public List<V> remove(Object... keys) {
		IndexMap myIndexer = MultiIndexHelper.getFinalIndexer(indexer, numberOfKeys, keys);
		if(myIndexer == null) return null;
		return (List<V>) myIndexer.remove(keys[keys.length - 1]);
	}
	
	@SuppressWarnings("unchecked")
	public V removeItem(V value, Object... keys) {
		ManyIndexer myIndexer = (ManyIndexer) MultiIndexHelper.getFinalIndexer(indexer, numberOfKeys, keys);
		if(myIndexer == null) return null;
		List<V> items = (List<V>) myIndexer.muttableGet(keys[keys.length - 1]);
		if(items == null) return null;
		return (items.remove(value))? value : null;
	}
	
	public List<V> getAll(Object... keys)
	{
		if(keys.length > numberOfKeys) throw new RuntimeException("Number of Keys : "+ numberOfKeys + " less than keys length: " + keys.length); 
		if(keys.length == numberOfKeys)
		{
			List<V> v = get(keys);
			if(v != null) return v;
		}
		IndexMap myIndexer = MultiIndexHelper.getIndexer(indexer, keys);
		List<V> rtnValue = new ArrayList<V>();
		if(myIndexer == null) return rtnValue;
		return MultiIndexHelper.getAll(rtnValue, myIndexer);
	}
	
	public boolean contains(Object... keys)
	{
		if(keys.length > numberOfKeys) throw new RuntimeException("Number of Keys : "+ numberOfKeys + " less than keys length: " + keys.length); 
		if(keys.length == numberOfKeys)
		{
			List<V> v = get(keys);
			if(v != null && !v.isEmpty()) return true;
		}
		IndexMap myIndexer = MultiIndexHelper.getIndexer(indexer, keys);
		if(myIndexer == null) return false;
		return MultiIndexHelper.contains(myIndexer);
	}
	
	@Override
	public String toString() {
		return "["+numberOfKeys+"]"+indexer.toString();
	}

	public void clear() {
		indexer.clear();
	}

	public Set<Entry<List<V>>> entrySet() {
		Object[] keys = new Object[getNumberOfKeys()];
		Set<Entry<List<V>>> entrySet = new HashSet<Entry<List<V>>>();
		return MultiIndexHelper.popluateEntrySet(entrySet, keys, indexer, 0);
	}

	public int getNumberOfKeys() {
		return numberOfKeys;
	}

	public Set keySet() {
		return indexer.keySet();
	}

	public Set keySet(int keyIndex) {
		if(keyIndex >= numberOfKeys) throw new RuntimeException("key Index should be less than number of Keys - " + numberOfKeys);
		if(keyIndex == 0) return keySet();
		return MultiIndexHelper.populateKeySet(new HashSet(), keyIndex, 0, indexer);
	}

}
