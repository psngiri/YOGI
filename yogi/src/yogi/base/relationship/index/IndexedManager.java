package yogi.base.relationship.index;

import java.util.List;
import yogi.base.Factory;
import yogi.base.FactoryListener;
import yogi.base.ObjectNotFoundException;
import yogi.base.indexing.Index;
import yogi.base.indexing.Indexer;
import yogi.base.relationship.RelationshipManager;

public abstract class IndexedManager<T extends Index<I>, I> extends RelationshipManager<T>{
	private Indexer<I, T> indexer;
	private final class MyFactoryListener implements FactoryListener<T> {
		public void add(T object) {
			indexer.index(object.getIndex(), object);
		}

		public boolean delete(T object) {
			indexer.remove(object.getIndex());
			return true;
		}

		public boolean clearAll() {
			indexer.clear();
			return true;
		}
	}
	
	
	protected IndexedManager() {
		this(new Indexer<I, T>());
	}

	protected IndexedManager(Indexer<I, T> indexer) {
		super();
		this.indexer = indexer;
	}

	public Indexer<I, T> getIndexer() {
		return indexer;
	}

	protected T getObject(I index) throws ObjectNotFoundException
	{
		T rtnValue = indexer.get(index);
		if(rtnValue == null)
		{
			throw new ObjectNotFoundException("Could not find object for index: "+  index);
		}
		return rtnValue;
	}

	protected List<T> getMatches(List<I> indexList)
	{
		return indexer.getMatches(indexList);
	}
	
	@Override
	public void setFactory(Factory<? extends T> factory) {
		super.setFactory(factory);
		factory.addFactoryListener(new MyFactoryListener());
	}

}
