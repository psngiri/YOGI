package yogi.base.indexing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class MultiIndexer<V> implements MultiIndexMap<V, V>{
	private IndexMap indexer = new Indexer();
	private int numberOfKeys;
	
	public MultiIndexer(int numberOfKeys) {
		super();
		this.numberOfKeys = numberOfKeys;
	}

	@SuppressWarnings("unchecked")
	public V index(V value, Object... keys) {
		IndexMap myIndexer = MultiIndexHelper.getIndexer(indexer, true, numberOfKeys-1, false, keys);
		return (V) myIndexer.index(keys[keys.length - 1], value);
	}

	/* (non-Javadoc)
	 * @see yogi.base.indexing.MultiMap#remove(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public V remove(Object... keys) {
		IndexMap myIndexer = MultiIndexHelper.getFinalIndexer(indexer, numberOfKeys, keys);
		if(myIndexer == null) return null;
		return (V) myIndexer.remove(keys[keys.length - 1]);
	}

	/* (non-Javadoc)
	 * @see yogi.base.indexing.MultiMap#get(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public V get(Object... keys) {
		IndexMap myIndexer = MultiIndexHelper.getFinalIndexer(indexer, numberOfKeys, keys);
		if(myIndexer == null) return null;
		Object object = myIndexer.get(keys[keys.length - 1]);
		return (V) object;
	}
	
	/* (non-Javadoc)
	 * @see yogi.base.indexing.MultiMap#getMatches(java.util.List)
	 */
	public List<V> getMatches(Collection... keys)
	{
		if(keys.length != numberOfKeys) throw new RuntimeException("Number of Keys : "+ numberOfKeys + " not equal to keys length: " + keys.length); 
		List<V> rtnValue = new ArrayList<V>();
		MultiIndexHelper.getMatches(rtnValue, indexer, keys);
		return rtnValue;
	}

	public V getFirstMatch(Collection... keys)
	{
		if(keys.length != numberOfKeys) throw new RuntimeException("Number of Keys : "+ numberOfKeys + " not equal to keys length: " + keys.length); 
		return (V) MultiIndexHelper.getFirstMatch(indexer, keys);
	}
	
	public List<V> getAll(Object... keys)
	{
		if(keys.length > numberOfKeys) throw new RuntimeException("Number of Keys : "+ numberOfKeys + " less than keys length: " + keys.length); 
		List<V> rtnValue = new ArrayList<V>();
		if(keys.length == numberOfKeys)
		{
			V v = get(keys);
			if(v != null) rtnValue.add(v);
			return rtnValue;
		}
		IndexMap myIndexer = MultiIndexHelper.getIndexer(indexer, keys);
		if(myIndexer == null) return rtnValue;
		return MultiIndexHelper.getAll(rtnValue, myIndexer);
	}
	
	@Override
	public boolean contains(Object... keys) {
		if(keys.length > numberOfKeys) throw new RuntimeException("Number of Keys : "+ numberOfKeys + " less than keys length: " + keys.length); 
		if(keys.length == numberOfKeys)
		{
			V v = get(keys);
			if(v != null) return true;
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

	public Set<Entry<V>> entrySet() {
		Object[] keys = new Object[getNumberOfKeys()];
		Set<Entry<V>> entrySet = new HashSet<Entry<V>>();
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
