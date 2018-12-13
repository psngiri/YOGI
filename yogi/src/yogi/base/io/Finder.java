package yogi.base.io;

import yogi.base.Creator;

public interface Finder<T, C extends Creator<T>> {
	T find(C creator, T object);
}
