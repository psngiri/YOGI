package yogi.server.gui.record.base.app.purge;

import yogi.base.Manager;
import yogi.base.app.BaseProcessor;
import yogi.base.relationship.RelationshipObject;
import yogi.server.gui.record.base.BaseRecord;
import yogi.server.gui.record.base.BaseRecordManager;
import yogi.server.gui.record.base.purge.BaseRecordPurgeAssistant;

/**
 * @author Vikram Vadavala
 *
 */
public class BaseRecordMarkPurgeProcessor<K extends RelationshipObject, T extends BaseRecord<K>> extends BaseProcessor 
{
	private Manager<K> keyManager;
	
	private BaseRecordManager<K, T> recordManager;

	private long timestamp;

	protected BaseRecordMarkPurgeProcessor(Manager<K> keyManager, BaseRecordManager<K, T> recordManager, long timestamp) {
		super();
		this.keyManager = keyManager;
		this.recordManager = recordManager;
		this.timestamp = timestamp;
	}

	@Override
	public void run() {
		BaseRecordPurgeAssistant.get().markForPurge(keyManager, recordManager, timestamp);
	}

	@Override
	public boolean isActivated() {
		return true;
	}


}