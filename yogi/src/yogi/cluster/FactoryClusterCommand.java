package yogi.cluster;

import java.util.List;

import yogi.base.Creator;
import yogi.base.validation.ObjectValidator;

public abstract class FactoryClusterCommand<T> extends ClusterCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1354005723849675710L;
	
	public FactoryClusterCommand(List<T> objects) {
		super();
	}
	
	public FactoryClusterCommand(T object) {
		super();
	}
	
	@Override
	public Boolean clusterExecute() {
		ClusterFactory<T> factory = getFactory();
		process(factory, getCreator());
		List<Creator<T>> creators = getCreators();
		if(creators != null){
			for(Creator<T> creator: creators){
				process(factory, creator);
			}
		}
		return true;
	}

	private void process(ClusterFactory<T> factory, Creator<T> creator) {
		if(creator == null) return;
		T rtnValue = factory.create(creator, getValidator());
		process(rtnValue);
	}

	protected abstract void process(T object);
	protected abstract List<Creator<T>> getCreators();
	protected abstract Creator<T> getCreator();
	protected abstract ClusterFactory<T> getFactory();
	protected ObjectValidator<T> getValidator(){
		return null;
	}
	
}
