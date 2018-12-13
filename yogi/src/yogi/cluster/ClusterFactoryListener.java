package yogi.cluster;

import java.util.List;

public interface ClusterFactoryListener<T>{
	void add(T object);
	void add(List<T> objects);
}
