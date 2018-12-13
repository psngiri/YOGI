package yogi.server.base.purge;

import yogi.base.Selector;

public class PurgeSelector<T extends Purgeable> implements Selector<T> {
	@Override
	public boolean select(T record) {
		return PurgeableAssistant.get().isPurgeable(record);
	}
	

}
