package yogi.base.relationship.index;

import java.util.ArrayList;
import java.util.List;

import yogi.base.Factory;
import yogi.base.FactoryListener;
import yogi.base.indexing.Index;
import yogi.base.indexing.ManyIndexer;
import yogi.base.relationship.RelationshipManager;
import yogi.base.util.immutable.ImmutableList;

public abstract class ManyIndexedManager<T extends Index<I>, I> extends RelationshipManager<T>{
	private ManyIndexer<I, T> manyIndexer = new ManyIndexer<I,T>();
	private final class MyFactoryListener implements FactoryListener<T> {
		public void add(T object) {
			manyIndexer.index(object.getIndex(), object);
		}

		public boolean delete(T object) {
			manyIndexer.removeItem(object.getIndex(), object);
			return true;
		}

		public boolean clearAll() {
			manyIndexer.clear();
			return true;
		}
	}
	
	protected ManyIndexedManager() {
		super();
	}
	
	public ManyIndexer<I, T> getManyIndexer() {
		return manyIndexer;
	}

	protected ImmutableList<T> getObjects(I index)
	{
		ImmutableList<T> rtnValue = manyIndexer.get(index);
		if(rtnValue == null) rtnValue = new ImmutableList<T>(new ArrayList<T>(0));
		return rtnValue;
	}
	
	protected List<T> getMatches(List<I> indexList)
	{
		return manyIndexer.getMatches(indexList);
	}
	
	@Override
	public void setFactory(Factory<? extends T> factory) {
		super.setFactory(factory);
		factory.addFactoryListener(new MyFactoryListener());
	}

}
