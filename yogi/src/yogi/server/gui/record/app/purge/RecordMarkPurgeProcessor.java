package yogi.server.gui.record.app.purge;

import yogi.base.app.BaseProcessor;
import yogi.server.gui.record.Record;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.KeyManager;
import yogi.server.gui.record.purge.RecordPurgeAssistant;

/**
 * @author Vikram Vadavala
 *
 */
public class RecordMarkPurgeProcessor<K extends Key,D extends RecordData, T extends Record<K,D>> extends BaseProcessor 
{
	private KeyManager<K> keyManager;
	
	private RecordManager<K, D, T> recordManager;

	private long timestamp;

	protected RecordMarkPurgeProcessor(KeyManager<K> keyManager, RecordManager<K, D, T> recordManager, long timestamp) {
		super();
		this.keyManager = keyManager;
		this.recordManager = recordManager;
		this.timestamp = timestamp;
	}

	@Override
	public void run() {
		RecordPurgeAssistant.get().markForPurge(keyManager, recordManager, timestamp);
	}

	@Override
	public boolean isActivated() {
		return true;
	}


}