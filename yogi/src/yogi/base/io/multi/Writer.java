package yogi.base.io.multi;

import java.util.List;

public interface Writer<T> {
	void write(List<T> items);
}
