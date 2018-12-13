package yogi.base;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Unique {	
	public static <T> List<T> unique(List<? extends T> items, Comparator<? super T> comparator)
	{
		Collections.sort(items, comparator);
		return Filter.filter(items, new UniqueSelector<T>(comparator));
	}
	
	static class UniqueSelector<T> implements Selector<T>
	{
		private T lastItem = null;
		private Comparator<? super T> comparator;
		public UniqueSelector(Comparator<? super T> comparator) {
			super();
			this.comparator = comparator;
		}
		@Override
		public boolean select(T item) {
			boolean rtnValue = false;
			if(lastItem == null){
				rtnValue = true;
			}else{
				rtnValue = comparator.compare(lastItem, item)!=0;
			}
			lastItem = item;
			return rtnValue;
		}
		
	}
}
