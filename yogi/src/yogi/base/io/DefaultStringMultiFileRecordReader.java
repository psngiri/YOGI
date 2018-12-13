package yogi.base.io;

import java.util.Collection;

import yogi.base.io.resource.MultiFileResource;

public class DefaultStringMultiFileRecordReader<T> extends DefaultReader<T, String> {
	public DefaultStringMultiFileRecordReader(MultiFileResource multiFileResource) {		
		super(new MultiFileReader(multiFileResource));
	}	
	
	public DefaultStringMultiFileRecordReader(MultiFileResource multiFileResource, int headerLineCount) {		
		super(new MultiFileReader(multiFileResource,headerLineCount));
	}	

	
	public DefaultStringMultiFileRecordReader(Collection<String> objects) {
		super(new RecordCollectionReader<String>(objects));
	}
}
