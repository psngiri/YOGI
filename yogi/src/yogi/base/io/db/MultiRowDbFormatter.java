package yogi.base.io.db;

import java.util.List;

public interface MultiRowDbFormatter<T>  extends BaseDbFormatter{
	  List<DbRecord> format(T object);
}
