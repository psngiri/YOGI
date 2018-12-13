package yogi.server.gui.record.base.app.purge;

import yogi.base.app.BaseProcessor;
import yogi.base.relationship.RelationshipObject;
import yogi.server.gui.record.base.BaseRecord;
import yogi.server.gui.record.base.BaseRecordManager;

/**
 * @author Vikram Vadavala
 *
 */
public class BaseRecordPurgeProcessor<K extends RelationshipObject, T extends BaseRecord<K>> extends BaseProcessor 
{
	
	private BaseRecordManager<K, T> recordManager;
	
	public BaseRecordPurgeProcessor(BaseRecordManager<K, T> recordManager) {
		super();
		this.recordManager = recordManager;
	}

	@Override
	public void run() {
		recordManager.purge();
	}

	@Override
	public boolean isActivated() {
		return true;
	}

}