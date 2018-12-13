package yogi.base.io.db;

import yogi.base.io.resource.db.DbResource;


public class DbWriter<T> extends BaseDbWriter<T, DbFormatter<T>> {
	public DbWriter(DbResource resource, DbFormatter<T> formatter) {
		super(resource, formatter);
	}

	public DbWriter(DbResource resource, DbFormatter<T> formatter, String dumpFileName) {
		super(resource, formatter, dumpFileName);
	}

	public boolean write(T object) {
		DbRecord record = getFormatter().format(object);
		execute(object, record);
		return true;
	}

}

