package yogi.base.io;

import yogi.base.Creator;

public interface CreatorScanner<T, C extends Creator<T>, R> {
  C scan(R record);
}
