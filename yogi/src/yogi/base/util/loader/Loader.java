package yogi.base.util.loader;

import java.util.Collection;

import yogi.base.io.DefaultStringRecordReader;
import yogi.base.io.PipeRecordProcessor;
import yogi.base.io.db.DbFormatter;
import yogi.base.io.db.DbWriter;
import yogi.base.io.resource.db.DbResource;

public class Loader extends DefaultStringRecordReader<String> {
	public static boolean RUN = true;
	
	public Loader(String fileName, DbResource resource, DbFormatter<String> formatter) {
		super(fileName);
		setup(resource, formatter);
	}
	
	public Loader(String fileName, int headerLineCount, DbResource resource, DbFormatter<String> formatter) {
		super(fileName, headerLineCount);
		setup(resource, formatter);
	}

	private void setup(DbResource resource, DbFormatter<String> formatter) {
		this.addRecordProcessor(new PipeRecordProcessor<String>(new DbWriter<String>(resource, formatter)));
	}

	public Loader(Collection<String> records, DbResource resource, DbFormatter<String> formatter) {
		super(records);
		setup(resource, formatter);
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}
}
