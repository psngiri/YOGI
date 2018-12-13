package yogi.base.io;

import java.util.Collection;

import yogi.base.io.resource.SystemResource;

public class DefaultStringRecordReader<T> extends DefaultReader<T, String> {
	public DefaultStringRecordReader(String fileName) {
		super(new FileRecordReader(fileName));
	}

	public DefaultStringRecordReader(String fileName, int headerLineCount) {
		super(new FileRecordReader(fileName, headerLineCount));
	}
	
	public DefaultStringRecordReader(SystemResource resource, int headerLineCount) {
		super(new FileRecordReader(resource, headerLineCount));
	}
	
	public DefaultStringRecordReader(SystemResource resource) {
		super(new FileRecordReader(resource));
	}
	
	public DefaultStringRecordReader(Collection<String> objects) {
		super(new RecordCollectionReader<String>(objects));
	}
}
