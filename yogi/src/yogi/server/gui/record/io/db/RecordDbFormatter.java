package yogi.server.gui.record.io.db;

import yogi.base.io.db.DbFormatter;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.ObjectDbRecord;
import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.ClassPathResource;
import yogi.base.util.JsonAssistant;
import yogi.server.action.ActionAssistant;
import yogi.server.gui.record.Record;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;


public abstract class RecordDbFormatter<K extends Key, D extends RecordData, T extends Record<K, D>> implements DbFormatter<T> {
	private ObjectDbRecord dbRecord = new ObjectDbRecord(10);
	
	public RecordDbFormatter() {
	}

	@Override
	public DbRecord format(T record) {
		dbRecord.setObject(0, record.getKey().getUser().getId());
		dbRecord.setObject(1, record.getKey().getIdName());
		dbRecord.setObject(2, record.getKey().getCreateDate());
		dbRecord.setObject(3, record.getTimeStamp());
		dbRecord.setObject(4, record.getDescription());
		dbRecord.setObject(5, record.getComments());
		dbRecord.setObject(6, record.getKey().getPartition().getPartitionCode());
		dbRecord.setObject(7, ActionAssistant.get().getActionCode(record.getAction()));
		dbRecord.setObject(8, JsonAssistant.get().toJson(record.getData()));
		dbRecord.setObject(9, record.getModifiedByUser().getId());
		return 	dbRecord;	
	}

	public String query() {		
		QueryReader queryReader = getQueryReader();
		return queryReader.read();
	}

	protected QueryReader getQueryReader() {
		return new QueryReader(new ClassPathResource("insertQuery.txt", this.getClass()));
	}


	public String cleanUpQuery() {
       	return null;		
	}
}