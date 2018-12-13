package yogi.server.gui.record.key.io;

import yogi.base.io.Finder;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.KeyCreator;

/**
 * @author Vikram Vadavala
 *
 */
public class KeyFinder<K extends Key,C extends KeyCreator<K>> implements Finder<K, C> {

	@Override
	public K find(C keyCreator, K prevKey) {
		if(prevKey!=null && keyCreator.getIdName().equals(prevKey.getIdName()) && (keyCreator.getUser()==prevKey.getUser()) && (keyCreator.getPartition()==prevKey.getPartition())){
			return prevKey;
		}
		return null;
	}

}