package yogi.base.io.db;

import java.util.List;

import yogi.base.io.resource.db.DbResource;


public class MultiRowDbWriter<T> extends BaseDbWriter<T, MultiRowDbFormatter<T>> {
	public MultiRowDbWriter(DbResource resource, MultiRowDbFormatter<T> formatter) {
		super(resource, formatter);
	}

	public boolean write(T object) {
		List<DbRecord> records = getFormatter().format(object);
		for(DbRecord record: records)
		{
			execute(object, record);
		}
		return true;
	}

}

