package yogi.base.io.api;

import java.util.List;

import yogi.base.io.db.DbRecord;
import yogi.base.io.db.MultiRowDbFormatter;


public class MultiRowAPIWriter<T> extends BaseAPIWriter<T, MultiRowDbFormatter<T>> {
	public MultiRowAPIWriter(APIResource resource, MultiRowDbFormatter<T> formatter) {
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

