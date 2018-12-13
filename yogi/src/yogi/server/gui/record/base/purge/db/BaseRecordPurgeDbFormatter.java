package yogi.server.gui.record.base.purge.db;

import yogi.base.io.db.DbRecord;
import yogi.base.io.db.ObjectDbRecord;
import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.ClassPathResource;
import yogi.server.base.purge.io.db.PurgeDbFormatter;
import yogi.server.gui.record.base.BaseRecord;

public abstract class BaseRecordPurgeDbFormatter<T extends BaseRecord<?>> extends PurgeDbFormatter<T> {

	protected ObjectDbRecord dbRecord;

	public BaseRecordPurgeDbFormatter(int count) {
		super();
		this.dbRecord = new ObjectDbRecord(count);
	}

	@Override
	public String query() {
		 QueryReader queryReader = new QueryReader(new ClassPathResource("purgeQuery.txt", this.getClass()));
		 queryReader.addVariable("tableType", getTableType());
		 return queryReader.read();
	}

	protected abstract String getTableType();

	public DbRecord format(T record) {
		dbRecord.clear();
		dbRecord.setObject(0, record.getTimeStamp());
		return dbRecord;
	}

}
