package yogi.base.io;

import java.util.Collection;

public interface ObjectWriter<T> {
	boolean open();
	boolean close();
	boolean write(T object);
	void write(Collection<T> objects);
	void setAppend(boolean append);
	int getNumberOfRecordsWriten();
}
