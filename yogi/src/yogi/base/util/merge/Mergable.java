package yogi.base.util.merge;

public interface Mergable<T> {
	boolean isMergable(T object);
	void merge(T object);
}
