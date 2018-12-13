package yogi.base.io;

import java.util.List;


public interface MultiLineFormatter<T> {
	  List<String> format(T object);
}
