package yogi.base.io;

import yogi.base.io.resource.Resource;

public interface RecordReader<T> {
  boolean open();
  boolean close();
  boolean hasNext();
  T next();
  Resource getResource();
}
