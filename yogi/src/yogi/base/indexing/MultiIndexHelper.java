package yogi.base.indexing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import yogi.base.indexing.MultiIndexMap.Entry;

public class MultiIndexHelper {
	@SuppressWarnings("unchecked")
	static IndexMap getIndexer(IndexMap myIndexer, Object... keys) {
		return getIndexer(myIndexer, false, keys.length, false, keys); 
	}
	
	@SuppressWarnings("unchecked")
	static IndexMap getIndexer(IndexMap myIndexer, boolean createIfNull, int location, boolean many, Object... keys) {
		for(int i = 0; i < location; i++)
		{
			Object key = keys[i];
			Object value = myIndexer.get(key);
			IndexMap tmpIndexer = (IndexMap) value;
			if(tmpIndexer == null)
			{
				if(!createIfNull) return null;
				if(many && i == keys.length-2)
				{
					tmpIndexer = new ManyIndexer();
				}else
				{
					tmpIndexer = new Indexer();
				}
				myIndexer.index(key, tmpIndexer);
			}
			myIndexer = tmpIndexer;
		}
		return myIndexer;
	}

	@SuppressWarnings("unchecked")
	static IndexMap getFinalIndexer(IndexMap myIndexer, int numberOfKeys, Object... keys) {
		if(numberOfKeys != keys.length) throw new RuntimeException("Number of Keys : "+ numberOfKeys + " not equal keys length: " + keys.length + " keys: " + Arrays.asList(keys));
		int last = keys.length - 1;
		return MultiIndexHelper.getIndexer(myIndexer, false, last, false, keys);
	}


	@SuppressWarnings("unchecked")
	static <V> List<V> getAll(List<V> rtnValue, IndexMap indexer)
	{
		for(Object object:indexer.values())
		{
			if(object instanceof IndexMap) getAll(rtnValue, (IndexMap)object);
			else rtnValue.add((V)object);
		}
		return rtnValue;
	}
		
	@SuppressWarnings("unchecked")
	static boolean contains(IndexMap indexer)
	{
		for(Object object:indexer.values())
		{
			if(object instanceof IndexMap){
				return contains((IndexMap)object);
			}else{
				if(object == null) return false;
				if(object instanceof Collection)
				{
					return !((Collection)object).isEmpty();
				}
				return true;
			}
		}
		return false;
	}
	

	@SuppressWarnings("unchecked")
	public static <T> Set<Entry<T>> popluateEntrySet(Set<Entry<T>> entrySet, Object[] keys, IndexMap myIndexMap, int level) {
		for(Object object: myIndexMap.entrySet())
		{
			Map.Entry entry = (Map.Entry)object;
			keys[level] = entry.getKey();
			Object value = entry.getValue();
			if(value instanceof IndexMap) 
			{
				popluateEntrySet(entrySet, keys, (IndexMap)value, level+1);
			}else
			{
				MultiIndexMap.Entry<T> myEntry = new MultiIndexMap.Entry<T>((T)value, keys.clone());
				entrySet.add(myEntry);
			}
		}
		return entrySet;
	}

	@SuppressWarnings("unchecked")
	static <V, T extends Collection<V>> T getMatches(T rtnValue, IndexMap indexer, Collection... keys) {
		List<IndexMap> indexers = new ArrayList<IndexMap>();
		indexers.add(indexer);
		int lastindex = keys.length -1;
		for(int i = 0; i < lastindex; i ++)
		{
			List<IndexMap> myIndexers = new ArrayList<IndexMap>();
			for(IndexMap myIndexer: indexers)
			{
				myIndexers.addAll(myIndexer.getMatches(keys[i]));
			}
			indexers = myIndexers;
		}
		for(IndexMap myIndexer: indexers)
		{
			rtnValue.addAll(myIndexer.getMatches(keys[lastindex]));
		}
		return rtnValue;
	}
	
	static <V> V getFirstMatch(IndexMap indexer, Collection... keys) {
		List<IndexMap> indexers = new ArrayList<IndexMap>();
		indexers.add(indexer);
		int lastindex = keys.length -1;
		for(int i = 0; i < lastindex; i ++)
		{
			List<IndexMap> myIndexers = new ArrayList<IndexMap>();
			for(IndexMap myIndexer: indexers)
			{
				myIndexers.addAll(myIndexer.getMatches(keys[i]));
			}
			indexers = myIndexers;
		}
		for(IndexMap myIndexer: indexers)
		{
			V v = (V) myIndexer.getFirstMatch(keys[lastindex]);
			if(v == null) continue;
			return v;
		}
		return null;
	}

	@SuppressWarnings({"unchecked"})
	static Set populateKeySet(Set keySet, int keyIndex, int level, IndexMap indexMap ) {
		for(Object object: indexMap.entrySet())
		{
			Map.Entry entry = (Map.Entry)object;
			if(keyIndex == level)
			{
				keySet.add(entry.getKey());
			}else
			{
				populateKeySet(keySet, keyIndex, level + 1, (IndexMap) entry.getValue());
			}
		}
		return keySet;
	}
}
