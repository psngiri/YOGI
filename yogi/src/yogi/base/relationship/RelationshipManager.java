package yogi.base.relationship;

import yogi.base.Factory;
import yogi.base.FactoryListener;

public abstract class RelationshipManager<T> extends BaseRelationshipManager<T>{
	private final class MyFactoryListener implements FactoryListener<T> {
		public void add(T object) {
			buildRelationships(object);
		}

		public boolean delete(T object) {
			RelationshipController.removeFromRelationships(object);
			deleteRelationships(object);
			return true;
		}

		public boolean clearAll() {
			return true;
		}
	}

	protected RelationshipManager() {
		super();
	}
	
	@Override
	public void setFactory(Factory<? extends T> factory) {
		super.setFactory(factory);
		factory.addFactoryListener( new MyFactoryListener());
	}
	
   protected void buildRelationships(T object)
   {
	   
   }
   
   protected void deleteRelationships(T object)
   {
	   
   }
      
}
