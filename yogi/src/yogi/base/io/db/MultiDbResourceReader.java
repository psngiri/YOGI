package yogi.base.io.db;

import yogi.base.io.MultiResourceReader;
import yogi.base.io.resource.db.DbResource;
import yogi.base.io.resource.db.MultiDbResource;

public class MultiDbResourceReader<T> extends MultiResourceReader<DbRecord> {

	private MultiDbRecordReader<T> reader;
	
	public MultiDbResourceReader(MultiDbResource multiDbResource, MultiDbRecordReader<T> reader) {
		super(multiDbResource);
		this.reader = reader;
	}

	@Override
	protected void resetCurrentReader() {
		setCurrentReader(new MyDbRecordReader(getResource().next(),reader));
	}

	@Override
	public MultiDbResource getResource() {
		return (MultiDbResource) super.getResource();
	}

	
	class MyDbRecordReader extends DbRecordReader
	{
		private MultiDbRecordReader<T> reader;		
		public MyDbRecordReader(DbResource resource, MultiDbRecordReader<T> reader) {
			super(resource);
			this.reader = reader;			
		}

		@Override
		public String getQuery() {
			return reader.getQuery();
		}		
	}
}
