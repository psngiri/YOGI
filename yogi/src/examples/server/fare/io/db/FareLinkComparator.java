package examples.server.fare.io.db;

import yogi.base.io.link.LinkComparator;

import examples.server.fare.Fare;
import examples.server.fare.FareCreator;
import examples.server.fare.farekey.FareKey;
/**
 * @author Vikram Vadavala
 *
 */

public class FareLinkComparator<K extends FareKey, T extends Fare, C extends FareCreator> implements LinkComparator<C, K> {

	/* 
	 * Sorts FareCreator objects
	 */
	public int compareFrom(C from1, C from2) {
		int result = from1.getKeyId()-from2.getKeyId();
		return result;
	}

	/* 
	 * Links both if they are equal
	 */
	public int compareFromTo(C from, K to) {
		int result = from.getKeyId()-to.getId();
		return result;
	}

	/* 
	 * Sorts FareKey objects
	 */
	public int compareTo(K to1, K to2) {
		int result = to1.getId()-to2.getId();
		return result;
	}

}
