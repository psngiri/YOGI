package yogi.server.gui.record.io;

import yogi.base.io.link.LinkComparator;
import yogi.server.gui.record.RecordCreator;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;

public abstract class BaseKeyLinkComparator<K extends Key, D extends RecordData, C extends RecordCreator<?, D, ?>> implements LinkComparator<C, K> {

	/* 
	 * Sorts StrategyCreator objects
	 */
	public int compareFrom(C from1, C from2) {
		K key1 = getKey(from1);
		K key2 = getKey(from2);
		int result = getUserId(key1).compareTo(getUserId(key2));
		if(result != 0) return result;
		result = getIdName(key1).compareTo(getIdName(key2));
		if(result != 0) return result;
		result = from1.getPartition().compareTo(from2.getPartition());
		if(result != 0) return result;
		Long timeStamp1 = from1.getTimeStamp();
		Long timeStamp2 = from2.getTimeStamp();
		return timeStamp1.compareTo(timeStamp2);
	}

	private String getUserId(K key){
		if(key != null) return key.getUserId();
		return "";
	}
	
	private String getIdName(K key){
		if(key != null) return key.getIdName();
		return "";
	}

	protected abstract K getKey(C creator);

	/* 
	 * Links both if they are equal
	 */
	public int compareFromTo(C from, K to) {
		K key = getKey(from);
		int result = getUserId(key).compareTo(to.getUser().getId());
		if(result != 0) return result;
		result = getIdName(key).compareTo(to.getIdName());
		if(result != 0) return result;
		return from.getPartition().compareTo(to.getPartition());
	}

	/* 
	 * Sorts ScopeKey objects
	 */
	public int compareTo(K to1, K to2) {
		int result = to1.getUser().getId().compareTo(to2.getUser().getId());
		if(result != 0) return result;
		result = to1.getIdName().compareTo(to2.getIdName());
		if(result != 0) return result;
		return to1.getPartition().compareTo(to2.getPartition());
	}

	

}
