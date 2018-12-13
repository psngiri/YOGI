package yogi.base.io;

import java.util.List;


public interface RecordProcessor<T, R> {
	T process(R record);
	int getValidRecordCount();
	List<T> getObjectsCreated();
	boolean open();
	boolean close();
}
