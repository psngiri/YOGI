package yogi.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import yogi.base.io.ObjectWriter;
import yogi.base.util.immutable.ImmutableList;
import yogi.base.validation.ObjectValidator;

public class Factory<T> {
	private List<T> objects;

	private List<FactoryListener<? super T>> factoryListeners = new ArrayList<FactoryListener<? super T>>();
	private static double InitialCapacityTimes = 1.1;
	private static List<Factory<?>> factories = new ArrayList<Factory<?>>();
	private static Map<Class<?>, Integer> factoryCapacities = new HashMap<Class<?>, Integer>();

	public static void setInitialCapacity(Class<?> klass, int initialCapacity)
	{
		factoryCapacities.put(klass, initialCapacity);
	}
	
	public static ImmutableList<Factory<?>> getFactories()
	{
		return new ImmutableList<Factory<?>>(factories);
	}
	
	protected List<T> getObjects() {
		return objects;
	}

	protected Factory(Manager<T> manager) {
		super();
		Integer initialSize = factoryCapacities.get(this.getClass());
		if(initialSize == null) initialSize = 10;
		initialSize = (int) (initialSize * InitialCapacityTimes);
		objects = new ArrayList<T>(initialSize);
		manager.setFactory(this);
		factories.add(this);
	}

	public static void clearAllFactories() {
		for (Factory<?> factory : factories) {
			factory.clearAll();
		}
	}

	protected void clearAll() {
		if(!fireClearAll()) return;
		objects.clear();
	}

	private boolean fireClearAll() {
		boolean success = true;
		for (FactoryListener<? super T> factoryListener : factoryListeners) {
			if(!factoryListener.clearAll()) success = false;
		}
		return success;
	}

	public void clear() {
		if(!fireClearAll()) return;
		ListIterator<T> iterator = this.objects.listIterator(objects.size());
		while(iterator.hasPrevious() ) {
			if ( processDelete(iterator.previous()) ){
				iterator.remove();
			}
		}
	}

	public T create(Creator<T> creator) {
		return create(creator, null);
	}

	public T create(Creator<T> creator, ObjectValidator<? super T> validator) {
		T object = creator.create();
		if(object == null) return null;
		if (validator == null || validator.validate(object))
		{
			add(object);
			return object;
		}
		return null;
	}

	public void addFactoryListener(FactoryListener<? super T> factoryListener) {
		if (factoryListeners.contains(factoryListener)) return;
		factoryListeners.add(factoryListener);
	}

	public void removeFactoryListener(FactoryListener<? super T> factoryListener) {
		factoryListeners.remove(factoryListener);
	}

	protected void add(T object) {
		objects.add(object);
		for (FactoryListener<? super T> factoryListener : factoryListeners) {
			factoryListener.add(object);
		}
	}

	public void delete(T object) {
		boolean success = processDelete(object);
		if(!success) return;
		objects.remove(object);
	}

	private boolean processDelete(T object) {
		boolean success = true;
		int i = factoryListeners.size()-1;
		for (; i >= 0; i--) {
			if(!factoryListeners.get(i).delete(object)) success = false;
		}
		return success;
	}

	public boolean isEmpty() {
		return objects.isEmpty();
	}
	
	public int size()
	{
		return objects.size();
	}

	public void write(ObjectWriter<T> writer) {
		write(writer, null, null);
	}

	public void write(ObjectWriter<T> writer, Selector<? super T> selector,	Comparator<T> comparator) {
		List<T> items = objects;
		if(comparator != null)
		{
			items = new ArrayList<T>(items);
			Collections.sort(items, comparator);
		}
		for (T object : items) {
			if (selector != null && !selector.select(object))
				continue;
			writer.write(object);
		}
	}

	public void purge(Selector<T> selector)
	{
		Util.purge(objects, selector);
	}
}
