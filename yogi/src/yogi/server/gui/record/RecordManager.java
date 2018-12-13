package yogi.server.gui.record;

import yogi.base.Factory;
import yogi.server.gui.record.base.BaseRecordManager;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.KeyManager;

/**
 * @author Vikram Vadavala
 *
 */

public abstract class RecordManager<K extends Key, D extends RecordData, T extends Record<K, D>> extends BaseRecordManager<K, T> {
	
	protected RecordManager() {
		super();
	}
	
	protected void purge(KeyManager<K> keyManager, Factory<T> recordFactory)
	{
		super.purge(keyManager, recordFactory);
	}

}
