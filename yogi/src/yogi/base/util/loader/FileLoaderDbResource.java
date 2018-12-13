package yogi.base.util.loader;

import yogi.base.io.PipeRecordProcessor;
import yogi.base.io.db.DbFormatter;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.db.dump.DbDumpFileRecordWriter;
import yogi.base.io.resource.SystemResource;
import yogi.base.io.resource.db.DbResource;

public class FileLoaderDbResource extends DefaultDbRecordReader<DbRecord> {
	public static boolean RUN = true;
	
	public FileLoaderDbResource(DbResource resource, SystemResource queryResource, String fileName) {
		super(resource, queryResource);
		setup(fileName);
	}
	
	public FileLoaderDbResource(DbResource resource, String fileName) {
		super(resource);
		setup(fileName);
	}
	
	private void setup(String fileName) {
//		this.addRecordProcessor(new PipeRecordProcessor<DbRecord>(new DbDumpFileRecordWriter(fileName)));
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}
}
