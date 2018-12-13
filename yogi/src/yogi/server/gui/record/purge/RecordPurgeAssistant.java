package yogi.server.gui.record.purge;

import yogi.base.app.ErrorReporter;
import yogi.server.action.Action;
import yogi.server.base.purge.Purgeable;
import yogi.server.base.purge.PurgeableAssistant;
import yogi.server.gui.record.Record;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.record.base.BaseRecord;
import yogi.server.gui.record.base.purge.BaseRecordPurgeAssistant;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.KeyManager;

public class RecordPurgeAssistant extends BaseRecordPurgeAssistant {

	private static RecordPurgeAssistant itsInstance = new RecordPurgeAssistant();

	public static RecordPurgeAssistant get() {
		return itsInstance;
	}

	public <K extends Key,D extends RecordData, T extends Record<K,D>> void markForPurge(KeyManager<K> keyManager, RecordManager<K, D, T> recordManager, long purgeTimestamp) {
		super.markForPurge(keyManager, recordManager, purgeTimestamp);
	}

	@Override
	protected <K> void markKeyForPurge(K key) {
		PurgeableAssistant.get().markForpurge((Purgeable)key);//Removing the key also as the last record is cancelled
	}

	@Override
	protected <K> void reportEmptyRecords(K key) {
		ErrorReporter.get().warning(key.getClass().getName() +" Keys without Records identified during Purge ", key);
	}

	@Override
	protected <T extends BaseRecord<?>> boolean canLastRecordBeDeleted(T lastRecord) {
		return lastRecord!=null && lastRecord.getAction()==Action.Cancel;
	}
		
}
