package yogi.base.util.loader;

import java.util.Collection;

import yogi.base.io.DefaultReader;
import yogi.base.io.FileRecordReader;
import yogi.base.io.PipeRecordProcessor;
import yogi.base.io.RecordCollectionReader;
import yogi.base.io.db.DbWriter;
import yogi.base.io.resource.db.DbResource;

public class QueryDataLoader extends DefaultReader<String, String>{
	
	public QueryDataLoader(String fileName, DbResource resource) {
		super(new FileRecordReader(fileName, 1));
		setup(resource, new QueryDataLoaderDbFormatter((FileRecordReader)this.getRecordReader()));
	}
	
	public QueryDataLoader(String query, Collection<String> records, DbResource resource) {
		super(new RecordCollectionReader<String>(records));
		setup(resource, new QueryDataLoaderDbFormatter(query, null));
	}
	
	private void setup(DbResource resource, QueryDataLoaderDbFormatter formatter) {
		this.addRecordProcessor(new PipeRecordProcessor<String>(new DbWriter<String>(resource, formatter)));
	}

}
