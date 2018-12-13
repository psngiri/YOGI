package yogi.base.util.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import yogi.base.util.scheduler.SchedulerAssistant;

public class ElementMapStore<K, T> {
	public static int StaleTime = 8*60*60*1000;
	public static int CleanEveryMinutes = 30;
	private Map<K, Element<K, T>> store = new HashMap<K, Element<K, T>>();
	
	public ElementMapStore() {
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
		List<Element<K, T>> staleElements = getStaleElements();
		removeStaleElements(staleElements);
	}
	
	public Element<K, T> add(T value){
		K key = generateKey();
		return add(key, value);
	}
	
	public Element<K, T> add(K key, T value) {
		Element<K, T> rtnValue = new Element<K, T>(key, value);
		store.put(key, rtnValue);
		return rtnValue;
	}
	public K generateKey(){
		throw new RuntimeException("Need to extend ElementMapStore and implement generateKey method if you are adding values without a key");
	}
	
	public Element<K, T> get(K key){
		return store.get(key);
	}
	
	private void removeStaleElements(List<Element<K, T>> elements){
		long staleTime = getStaleTime();
		for(Element<K, T> element: elements){
			if(element.getTimeStamp()< staleTime)
			{
				store.remove(element);
			}
		}
	}
	
	private List<Element<K, T>> getStaleElements(){
		List<Element<K, T>> rtnValue = new ArrayList<Element<K, T>>();
		long staleTime = getStaleTime();
		for(Element<K, T> element: store.values()){
			if(element.getTimeStamp()< staleTime)
			{
				rtnValue.add(element);
			}
		}
		return rtnValue;
	}
	private long getStaleTime() {
		long staleTime = System.currentTimeMillis()- StaleTime;
		return staleTime;
	}
}
