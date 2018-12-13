package yogi.base.app.multithread;

import java.lang.reflect.Constructor;
import java.util.Collection;


public class MultiCaller<T> extends BaseMultiCaller<T, Callable<T>>{
	public <M extends Callable<T>> void call(Collection<T> items, Class<M> klass, Object... parameters){
		call(ThreadPoolExecutor.NumberOfThreads, items, klass, parameters);
	}
	
	public <M extends Callable<T>> void call(int numberOfThreads, Collection<T> items, Class<M> klass, Object... parameters)
	{
		for(int i = 0; i < numberOfThreads; i ++){
			addCallable(getCallable(klass, parameters));
		}
		call(items);
	}

	private <M extends Callable<T>> Callable<T> getCallable(Class<M> klass, Object... parameters) {
		Callable<T> callable = null;
		try {
			Class<?>[] parameterTypes = new Class[parameters.length];
			for(int i = 0; i < parameters.length; i ++ ){
				parameterTypes[i] = Object.class;
			}
			Constructor<M> constructor = klass.getConstructor(parameterTypes);
			callable = constructor.newInstance(parameters);
		} catch (Exception e) {
			throw new RuntimeException("Could not construct class: " + klass.getCanonicalName(), e);
		} 
		return callable;
	}
	
}
