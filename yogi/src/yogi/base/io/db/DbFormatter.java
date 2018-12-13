package yogi.base.io.db;


public interface DbFormatter<T> extends BaseDbFormatter{
	  DbRecord format(T object);
}
