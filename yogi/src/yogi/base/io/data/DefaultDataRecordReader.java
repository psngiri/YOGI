package yogi.base.io.data;

import java.io.DataInput;

import yogi.base.io.DefaultReader;
import yogi.base.io.resource.SystemResource;

public class DefaultDataRecordReader<T> extends DefaultReader<T, DataInput> {
	public DefaultDataRecordReader(String fileName) {
		super(new DataRecordReader(fileName));
	}

	public DefaultDataRecordReader(SystemResource resource) {
		super(new DataRecordReader(resource));
	}
	
//	public DefaultDataRecordReader(Collection<String> objects) {
//		super(new RecordCollectionReader<String>(objects));
//	}
}
