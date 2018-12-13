package yogi.base;

public interface FactoryListener<T> {
	   void add(T object);
	   boolean delete(T object);
	   boolean clearAll();

}
