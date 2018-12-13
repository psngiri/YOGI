package yogi.server.gui.record.purge.db;

import yogi.base.io.db.DbRecord;
import yogi.base.io.db.ObjectDbRecord;
import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.ClassPathResource;
import yogi.server.base.purge.io.db.PurgeDbFormatter;
import yogi.server.gui.record.Record;

/**
 * @author Vikram Vadavala
 *
 * @param <T>
 */
public class RecordPurgeDbFormatter<T extends Record<?,?>> extends PurgeDbFormatter<T> {
	protected ObjectDbRecord dbRecord = new ObjectDbRecord(5);
	private String type="";

	public RecordPurgeDbFormatter(String type) {
		super();
		this.type=type;
	}
	
	@Override
	public String query() {
		 QueryReader queryReader = new QueryReader(new ClassPathResource("purgeQuery.txt", RecordPurgeDbFormatter.class));
		 queryReader.addVariable("tableName",getTableName().trim());
		 return queryReader.read();
	}

	protected String getTableName() {
		return "STRATEGIES";
	}

	public DbRecord format(T record) {
		dbRecord.clear();
		dbRecord.setObject(0, type);
		dbRecord.setObject(1, record.getKey().getIdName());
		dbRecord.setObject(2, record.getKey().getUser().getId());
		dbRecord.setObject(3, record.getTimeStamp());
		dbRecord.setObject(4, record.getPartition().getPartitionCode());
		return dbRecord;
	}

}
