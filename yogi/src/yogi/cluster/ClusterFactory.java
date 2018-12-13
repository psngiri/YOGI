package yogi.cluster;

import java.util.ArrayList;
import java.util.List;

import yogi.base.Factory;
import yogi.base.Manager;

public abstract class ClusterFactory<T> extends Factory<T> {
	
	private List<ClusterFactoryListener<T>> clusterFactoryListeners = new ArrayList<ClusterFactoryListener<T>>();

	protected ClusterFactory(Manager<T> manager) {
		this(manager, false);
	}

	protected ClusterFactory(Manager<T> manager, boolean addForClustering) {
		super(manager);
		if(addForClustering){
			ClusterManager.get().addFactoryForClusteing(this);
		}
	}

	void addClusterFactoryListener(ClusterFactoryListener<T> factoryListener) {
		if(!clusterFactoryListeners.contains(factoryListener)) clusterFactoryListeners.add(factoryListener);
	}

	void removeClusterFactoryListener(ClusterFactoryListener<T> factoryListener) {
		clusterFactoryListeners.remove(factoryListener);
	}

	@Override
	protected void add(T object) {
		super.add(object);
		for (ClusterFactoryListener<T> factoryListener : clusterFactoryListeners) {
			factoryListener.add(object);
		}
	}

	protected void add(List<T> objects) {
		for(T object: objects){
			super.add(object);
		}
		for (ClusterFactoryListener<T> factoryListener : clusterFactoryListeners) {
			factoryListener.add(objects);
		}
	}
	
	public T getLatestObject(){
		int size = this.getObjects().size();
		return this.getObjects().get(size-1);
	}
}