package yogi.base.util.loader;

import java.util.Collection;

import yogi.base.io.PipeRecordProcessor;
import yogi.base.io.db.DbFormatter;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DbWriter;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.resource.db.DbResource;

public class DbLoader extends DefaultDbRecordReader<Object> {
	public static boolean RUN = true;
	
	public DbLoader(DbResource sourceResource, DbResource resource, DbFormatter<DbRecord> formatter) {
		super(sourceResource);
		setup(resource, formatter);
	}

	private void setup(DbResource resource, DbFormatter<DbRecord> formatter) {
		PipeRecordProcessor<DbRecord> pipeRecordProcessor = new PipeRecordProcessor<DbRecord>(new DbWriter<DbRecord>(resource, formatter));
		this.addRecordProcessor(pipeRecordProcessor);
	}

	public DbLoader(Collection<DbRecord> records, DbResource resource, DbFormatter<DbRecord> formatter) {
		super(records);
		setup(resource, formatter);
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
}
