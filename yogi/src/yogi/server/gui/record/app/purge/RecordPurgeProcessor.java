package yogi.server.gui.record.app.purge;

import yogi.base.app.BaseProcessor;
import yogi.server.gui.record.Record;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.KeyManager;

/**
 * @author Vikram Vadavala
 *
 */
public class RecordPurgeProcessor<K extends Key,D extends RecordData, T extends Record<K,D>> extends BaseProcessor 
{
	private KeyManager<K> keyManager;
	
	private RecordManager<K, D, T> recordManager;
	
	public RecordPurgeProcessor(KeyManager<K> keyManager, RecordManager<K, D, T> recordManager) {
		super();
		this.keyManager = keyManager;
		this.recordManager = recordManager;
	}

	@Override
	public void run() {
		keyManager.purge();
		recordManager.purge();
	}

	@Override
	public boolean isActivated() {
		return true;
	}

}