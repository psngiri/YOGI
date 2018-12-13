package yogi.server.gui.record;

import java.util.List;

import yogi.base.Manager;
import yogi.base.validation.ObjectValidator;
import yogi.server.gui.record.base.BaseRecordCreator;
import yogi.server.gui.record.base.BaseRecordFactory;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.user.User;

public abstract class RecordFactory<K extends Key, D extends RecordData, T extends Record<K, D>> extends BaseRecordFactory<K, T> {
	
	protected RecordFactory(Manager<T> manager) {
		this(manager, false);
	}

	protected RecordFactory(Manager<T> manager, boolean addForClustering) {
		super(manager, addForClustering);
	}

	@Override
	public synchronized List<Integer> create(List<BaseRecordCreator<K, T>> creators, ObjectValidator<T> validator, User user) {
		throw new RuntimeException("Feature desabled for Record Factory");
	}
	
}