package yogi.base;

import java.util.ArrayList;
import java.util.List;

import yogi.base.util.immutable.ImmutableList;

public class Manager<T>{
	private Factory<? extends T> factory;
		
   protected Manager() {
		super();
	}

   public void setFactory(Factory<? extends T> factory) {
		this.factory = factory;
	}

   public boolean isEmpty()
   {
	   if(factory == null) return true;
	   return factory.isEmpty();
   }
   
   public int size()
   {
	   if(factory == null) return 0;
	   return factory.size();
   }
   
   public ImmutableList<T> findAll()
   {
	   if(factory == null) return new ImmutableList<T>(new ArrayList<T>());
	   return new ImmutableList<T>(factory.getObjects());
   }
   
   public List<T> findAll(boolean makeACopy)
   {
	   if(!makeACopy) return findAll();
	   if(factory == null) return new ArrayList<T>();
	   return new ArrayList<T>(factory.getObjects());
   }
   
   public List<T> find(Selector<? super T> selector)
   {
	   if(factory == null) return new ArrayList<T>();
	   return Filter.filter(new ImmutableList<T>(factory.getObjects()), selector);
   }
   
}
