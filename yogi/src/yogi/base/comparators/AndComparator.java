package yogi.base.comparators;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class AndComparator<T> implements Comparator<T>{
	List<Comparator<? super T>> comparators = new ArrayList<Comparator<? super T>>();
	
	public AndComparator(Comparator<? super T> ... comparators) {
		super();
		for(Comparator<? super T> comparator: comparators)
		{
			this.comparators.add(comparator);
		}
	}

	public AndComparator() {
		super();
	}
	
	public void add(Comparator<? super T> comparator)
	{
		this.comparators.add(comparator);
	}

	public int compare(T o1, T o2) {
		int compare = 0;
		for(Comparator<? super T> comparator: comparators)
		{
			if(comparator == null) continue;
			compare = comparator.compare(o1, o2);
			if(compare != 0) return compare;
		}
		return compare;
	}

}
