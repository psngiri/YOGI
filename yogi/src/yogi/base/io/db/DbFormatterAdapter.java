package yogi.base.io.db;

public abstract class DbFormatterAdapter<T> implements DbFormatter<T> {
	private ObjectDbRecord objectDbRecord;
	public DbFormatterAdapter(int size) {
		super();
		objectDbRecord = new ObjectDbRecord(size);
	}

	public ObjectDbRecord getObjectDbRecord() {
		objectDbRecord.clear();
		return objectDbRecord;
	}

}
