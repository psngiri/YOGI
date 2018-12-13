package yogi.server.gui.record.base.purge;

import yogi.base.Manager;
import yogi.base.relationship.RelationshipObject;
import yogi.base.util.immutable.ImmutableList;
import yogi.server.base.purge.PurgeableAssistant;
import yogi.server.gui.record.base.BaseRecord;
import yogi.server.gui.record.base.BaseRecordManager;

public class BaseRecordPurgeAssistant {
	
	private static BaseRecordPurgeAssistant itsInstance = new BaseRecordPurgeAssistant();

	public static BaseRecordPurgeAssistant get() {
		return itsInstance;
	}

	
	public <K extends RelationshipObject, T extends BaseRecord<K>> void markForPurge(Manager<K> keyManager, BaseRecordManager<K, T> recordManager, long purgeTimestamp)
	{
		for(K key: keyManager.findAll())
		{
			ImmutableList<T> records = recordManager.getRecords(key);
			markObjectsForPurge(key, records, purgeTimestamp);
		}
	}

	private <K extends RelationshipObject, T extends BaseRecord<K>> void markObjectsForPurge(K key, ImmutableList<T> records, long purgeTimestamp)
	{
			int size = records.size();
			if(size == 0) {//Theoretically this case should never happen
				reportEmptyRecords(key);
				return;
			}
			boolean flag = false;
			T lastRecord = null;
			for(int i = 0; i < size; i ++)
			{
				T record = records.get(i);
				if(record.getTimeStamp() < purgeTimestamp)
				{
					if(lastRecord != null){
						PurgeableAssistant.get().markForpurge(lastRecord);
					}
					lastRecord = record;
				} else {
					flag = true;
					break;
				}
			}
			if(flag) return;//Mark the last Record for purge only If all the records for the key is marked for purge
			if(canLastRecordBeDeleted(lastRecord))//Mark the Last Record for Purge only it is cancelled
			{
				PurgeableAssistant.get().markForpurge(lastRecord);
				markKeyForPurge(key);
			}
	}


	protected <K> void markKeyForPurge(K key) {};

	protected <K> void reportEmptyRecords(K key) {};

	protected <T extends BaseRecord<?>> boolean canLastRecordBeDeleted(T lastRecord) {
		return false;
	}
		
}
