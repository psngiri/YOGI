package yogi.base.util.merge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MergeManager {
		
	public static <T> List<T> merge(List<T> items, Merger<T> merger, Comparator<T> comparator)
	{
		if(merger == null) throw new RuntimeException("Merger needs to be set before calling merge");
		Collections.<T> sort(items, comparator);
		if(items.size() <= 1) return new ArrayList<T>(items);
		T previousItem = items.get(0);
		List<T> returnValue = new ArrayList<T>();
		for(Iterator<T> iter = items.listIterator(1); iter.hasNext();)
		{
			T item = iter.next();
			
			if(merger.isMergable(item, previousItem))
			{
				previousItem = merger.merge(previousItem, item);
			}else
			{
				returnValue.add(previousItem);
				previousItem = item;
			}
		}
		returnValue.add(previousItem);
		return returnValue;

	}

	public static <M extends Mergable<M>>void merge(List<M> items, Comparator<M> comparator)
	{
		Collections.<M> sort(items, comparator);
		if(items.size() <= 1) return;
		M previousItem = items.get(0);
		for(Iterator<M> iter = items.listIterator(1); iter.hasNext();)
		{
			M item = iter.next();
			if(item.isMergable(previousItem))
			{
				previousItem.merge(item);
				iter.remove();
			}else
			{
				previousItem = item;
			}
		}
		
	}
}
