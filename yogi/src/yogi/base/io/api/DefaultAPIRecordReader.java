package yogi.base.io.api;

import java.util.Collection;

import yogi.base.io.DefaultReader;
import yogi.base.io.RecordCollectionReader;
import yogi.base.io.db.DbRecord;

public class DefaultAPIRecordReader<T> extends DefaultReader<T, DbRecord> {

	public DefaultAPIRecordReader(APIResource resource) {
		setRecordReader(new DefaultDumpAPIRecordReader<T>(resource, this));
	}

	public DefaultAPIRecordReader(Collection<DbRecord> objects) {
		super(new RecordCollectionReader<DbRecord>(objects));
	}
	
}
