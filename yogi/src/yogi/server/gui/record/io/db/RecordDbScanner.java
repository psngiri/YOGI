package yogi.server.gui.record.io.db;

import yogi.base.app.ErrorReporter;
import yogi.base.io.CreatorScanner;
import yogi.base.io.db.DbRecord;
import yogi.base.util.JsonAssistant;
import yogi.server.action.ActionAssistant;
import yogi.server.gui.partition.PartitionManager;
import yogi.server.gui.record.Record;
import yogi.server.gui.record.RecordCreator;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.user.UserManager;

public abstract class RecordDbScanner<K extends Key, D extends RecordData, T extends Record<K, D>, C extends RecordCreator<K, D, T>> implements CreatorScanner<T, C, DbRecord> {

	public C scan(DbRecord dbRecord) {
		C creator = getCreator();
		try {
			creator.setUser(UserManager.get().getUser(dbRecord.getString(1)));
			creator.setIdName(dbRecord.getString(2));
			creator.setPartition(PartitionManager.get().getPartition(dbRecord.getString(3)));
			creator.setTimeStamp(dbRecord.getLong(4));
			creator.setDescription(dbRecord.getString(5));
			creator.setComments(dbRecord.getString(6));
			creator.setAction(ActionAssistant.get().getAction(dbRecord.getInt(7)));
			D data = JsonAssistant.get().fromJson(dbRecord.getString(8), getRecordDataClass());
			creator.setData(data);
			String modifiedByUserId = dbRecord.getString(9);
			creator.setModifiedByUser(UserManager.get().getUser(modifiedByUserId));
		} catch (Exception e) {
			ErrorReporter.get().error(dbRecord, e);
		}
		return creator;
	}
	
	protected abstract Class<D> getRecordDataClass();
	protected abstract C getCreator();
	
}
