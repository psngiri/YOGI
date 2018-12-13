package yogi.server.gui.record.io;

import yogi.base.io.link.LinkComparator;
import yogi.server.gui.record.Record;
import yogi.server.gui.record.RecordCreator;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;

/**
 * @author Vikram Vadavala
 *
 */

public class RecordLinkComparator<K extends Key, D extends RecordData, T extends Record<K, D>, C extends RecordCreator<K, D, T>> implements LinkComparator<C, K> {

	/* 
	 * Sorts RecordCreator objects
	 */
	public int compareFrom(C from1, C from2) {
		int result = from1.getUser().compareTo(from2.getUser());
		if(result != 0) return result;
		result = from1.getIdName().compareTo(from2.getIdName());
		if(result != 0) return result;
		result = from1.getPartition().compareTo(from2.getPartition());
		if(result != 0) return result;
		Long timeStamp1 = from1.getTimeStamp();
		Long timeStamp2 = from2.getTimeStamp();
		return timeStamp1.compareTo(timeStamp2);
	}

	/* 
	 * Links both if they are equal
	 */
	public int compareFromTo(C from, K to) {
		int result = from.getUser().compareTo(to.getUser());
		if(result != 0) return result;
		result = from.getIdName().compareTo(to.getIdName());
		if(result != 0) return result;
		return from.getPartition().compareTo(to.getPartition());
	}

	/* 
	 * Sorts Key objects
	 */
	public int compareTo(K to1, K to2) {
		int result = to1.getUser().getId().compareTo(to2.getUser().getId());
		if(result != 0) return result;
		result = to1.getIdName().compareTo(to2.getIdName());
		if(result != 0) return result;
		return to1.getPartition().compareTo(to2.getPartition());
	}

}
