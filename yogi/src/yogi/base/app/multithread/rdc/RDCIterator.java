package yogi.base.app.multithread.rdc;

import yogi.base.util.collections.IndexItem;


public interface RDCIterator<E> {
    boolean hasNext();
    E next(IndexItem<E> lastIndexItem);
}
