package yogi.base.util.store;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import yogi.base.util.scheduler.SchedulerAssistant;

public class BaseListStore<T, E extends Element<Integer, T>> {
	public static int StaleTimeMilliSeconds = 8*60*60*1000;
	public static int CleanEveryMinutes = 30;
	private List<E> store = new ArrayList<E>();
	public BaseListStore() {
		super();
		Runnable command = new Runnable(){

			@Override
			public void run() {
				clearStaleElements();
			}

		};
		TimeUnit unit = TimeUnit.MINUTES;
		SchedulerAssistant.getScheduler().scheduleAtFixedRate(command, 1, CleanEveryMinutes, unit);
	}
	public void clearStaleElements() {
		List<E> staleElements = getStaleElements();
		removeStaleElements(staleElements);
	}
		
	protected E set(Integer key, E rtnValue) {
		if(key < store.size()){
			store.set(key, rtnValue);
		}else{
			throw new RuntimeException(String.format("key %s cannot be greater than %s", key, store.size()));
		}
		return rtnValue;
	}
	
	public synchronized Integer generateKey(){
		int size = store.size();
		store.add(null);
		return size;
	}
	
	protected E get(Integer key){
		return store.get(key);
	}
	
	private void removeStaleElements(List<E> elements){
		long staleTime = getStaleTime();
		for(E element: elements){
			if(element.getTimeStamp()< staleTime)
			{
				store.set(element.getKey(), null);
			}
		}
	}
	
	private List<E> getStaleElements(){
		List<E> rtnValue = new ArrayList<E>();
		long staleTime = getStaleTime();
		for(E element: store){
			if(element == null) continue;
			if(element.getTimeStamp()< staleTime)
			{
				rtnValue.add(element);
			}
		}
		return rtnValue;
	}
	private long getStaleTime() {
		long staleTime = System.currentTimeMillis()- StaleTimeMilliSeconds;
		return staleTime;
	}
	
	public void clear(){
		store.clear();
	}
}
