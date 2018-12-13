package yogi.base.io;

import yogi.base.Creator;

public interface CreatorObjectProcessor<T, C extends Creator<T>> {
	void process(C creator, T object, boolean objectNewlyCreated);
}
