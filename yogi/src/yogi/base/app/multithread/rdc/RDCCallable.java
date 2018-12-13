package yogi.base.app.multithread.rdc;

public interface RDCCallable<T, R> {
	boolean open();
	R call(T item);
	boolean close();
}
