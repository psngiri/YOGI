package yogi.base.util.merge;

public interface Merger<T> {
	boolean isMergable(T object1, T object2);
	T merge(T object1, T object2);
}
