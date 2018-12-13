package yogi.base.io.data;

import java.io.DataOutput;

public interface DataFormatter<T> {
	public boolean format(T object, DataOutput dataOutput);
}
