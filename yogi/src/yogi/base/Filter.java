package yogi.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Filter {	
	public static <T> List<T> filter(List<? extends T> items, Selector<? super T> selector)
	{
		List<T> rtnValue = new ArrayList<T>();
		if(selector == null){
			rtnValue.addAll(items);
			return rtnValue;
		}
		for(T item: items)
		{
			if(selector.select(item)) 
			{
				rtnValue.add(item);
			}
		}
		return rtnValue;
	}
	
	public static <T> List<T> filterRemoveSelected(List<? extends T> items, Selector<? super T> selector)
	{
		List<T> rtnValue = new ArrayList<T>();
		if(selector == null){
			rtnValue.addAll(items);
			return rtnValue;
		}
		Iterator<? extends T> iter = items.iterator();
		while(iter.hasNext())
		{
			T item = iter.next();
			if(selector.select(item)) 
			{
				rtnValue.add(item);
				iter.remove();
			}
		}
		return rtnValue;
	}
}
