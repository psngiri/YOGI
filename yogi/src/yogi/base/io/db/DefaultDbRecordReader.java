package yogi.base.io.db;

import java.util.Collection;
import java.util.List;

import yogi.base.io.DefaultReader;
import yogi.base.io.RecordCollectionReader;
import yogi.base.io.resource.ClassPathResource;
import yogi.base.io.resource.SystemResource;
import yogi.base.io.resource.db.DbResource;

public class DefaultDbRecordReader<T> extends DefaultReader<T, DbRecord> {
	private SystemResource queryResource = new ClassPathResource("query.txt", this.getClass());

	public DefaultDbRecordReader(DbResource resource, SystemResource queryResource) { 
		this(resource);
		this.queryResource = queryResource;
	}
	
	public DefaultDbRecordReader(DbResource resource) {
		setRecordReader(new DefaultDumpDbRecordReader<T>(resource, this));
	}

	protected SystemResource getQueryResource() {
		return queryResource;
	}

	@Override
	public List<T> read() {
		return super.read();
	}

	protected String getQuery() {
		return getQueryReader().read();
	}

	protected QueryReader getQueryReader() {
		return new QueryReader(getQueryResource());
	}

	public DefaultDbRecordReader(Collection<DbRecord> objects) {
		super(new RecordCollectionReader<DbRecord>(objects));
	}
	
}
