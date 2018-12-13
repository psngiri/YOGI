package yogi.base.comparators;

import java.util.Comparator;


public class ReverseComparator<T> implements Comparator<T>{
	Comparator<? super T> comparator;
	
	public ReverseComparator(Comparator<? super T> comparator) {
		super();
		this.comparator = comparator;
	}

	public int compare(T o1, T o2) {
		return - comparator.compare(o1, o2);
	}

}
