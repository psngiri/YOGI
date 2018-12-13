package yogi.base.relationship.index;

import java.util.List;

import yogi.base.Factory;
import yogi.base.FactoryListener;
import yogi.base.ObjectNotFoundException;
import yogi.base.indexing.MultiIndex;
import yogi.base.indexing.MultiIndexer;
import yogi.base.relationship.RelationshipManager;

public abstract class MultiIndexedManager<T extends MultiIndex> extends RelationshipManager<T>{
	private MultiIndexer<T> multiIndexer;
	private final class MyFactoryListener implements FactoryListener<T> {
		public void add(T object) {
			multiIndexer.index(object, object.getIndexes());
		}

		public boolean delete(T object) {
			multiIndexer.remove(object.getIndexes());
			return true;
		}

		public boolean clearAll() {
			multiIndexer.clear();
			return true;
		}
	}
	
	protected MultiIndexedManager(int numberOfKeys) {
		this(new MultiIndexer<T>(numberOfKeys));
	}
	
	protected MultiIndexedManager(MultiIndexer<T> multiIndexer) {
		super();
		this.multiIndexer = multiIndexer;
	}

	public MultiIndexer<T> getMultiIndexer() {
		return multiIndexer;
	}

	protected T getObject(Object... indexes) throws ObjectNotFoundException
	{
		T rtnValue = multiIndexer.get(indexes);
		if(rtnValue == null) 
		{
			String message = "Could not find object for indexes: " + indexes;
			throw new ObjectNotFoundException(message);
		}
		return rtnValue;
	}
	
	protected List<T> getMatches(List<?>... indexes)
	{
		return multiIndexer.getMatches(indexes);
	}
	
	@Override
	public void setFactory(Factory<? extends T> factory) {
		super.setFactory(factory);
		factory.addFactoryListener(new MyFactoryListener());
	}

}
