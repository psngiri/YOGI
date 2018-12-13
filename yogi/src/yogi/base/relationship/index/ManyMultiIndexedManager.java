package yogi.base.relationship.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yogi.base.Factory;
import yogi.base.FactoryListener;
import yogi.base.indexing.ManyMultiIndexer;
import yogi.base.indexing.MultiIndex;
import yogi.base.relationship.RelationshipManager;
import yogi.base.util.immutable.ImmutableList;

public abstract class ManyMultiIndexedManager<T extends MultiIndex> extends RelationshipManager<T>{
	private ManyMultiIndexer<T> manyMultiIndexer;
	private static ThreadLocal<Map<String, List<Object>>> listsTL = new ThreadLocal<Map<String, List<Object>>>()
	{
        protected synchronized Map<String, List<Object>> initialValue() {
            return new HashMap<String, List<Object>>();
        }
	};

	private final class MyFactoryListener implements FactoryListener<T> {
		public void add(T object) {
			manyMultiIndexer.index(object, object.getIndexes());
		}

		public boolean delete(T object) {
			manyMultiIndexer.removeItem(object, object.getIndexes());
			return true;
		}

		public boolean clearAll() {
			manyMultiIndexer.clear();
			return true;
		}
	}
	
	protected ManyMultiIndexedManager(int numberOfKeys) {
		super();
		manyMultiIndexer = new ManyMultiIndexer<T>(numberOfKeys);
	}
	
	public ManyMultiIndexer<T> getManyMultiIndexer() {
		return manyMultiIndexer;
	}

	protected ImmutableList<T> getObjects(Object... indexes)
	{
		ImmutableList<T> rtnValue = manyMultiIndexer.get(indexes);
		if(rtnValue == null) rtnValue = new ImmutableList<T>(new ArrayList<T>(0));
		return rtnValue;
	}
	
	protected List<T> getMatches(List<?>... indexes)
	{
		return manyMultiIndexer.getMatches(indexes);
	}
		
	public void setFactory(Factory<? extends T> factory) {
		super.setFactory(factory);
		factory.addFactoryListener(new MyFactoryListener());
	}

	@SuppressWarnings("unchecked")
	protected <T> ArrayList<T> getList(String name, T item, T... any) {
		List<Object> items = listsTL.get().get(name);
		if(items == null){
			items = new ArrayList<Object>(2);
			items.add(item);
			for(T my:any){
				items.add(my);
			}
			listsTL.get().put(name, items);
		}else{
			items.set(0, item);
		}
		return (ArrayList<T>) items;
	}

}
