package yogi.base.app.multithread;

public interface Callable<T> {
	boolean open();
	void call(T item);
	boolean close();
}
