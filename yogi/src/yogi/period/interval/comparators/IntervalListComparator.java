package yogi.period.interval.comparators;

import java.util.Comparator;
import java.util.List;

import yogi.period.interval.Interval;

public class IntervalListComparator implements Comparator<List<Interval>>
{

	public int compare(List<Interval> o1, List<Interval> o2) {
		if(o1.isEmpty()) return -1;
		if(o2.isEmpty()) return 1;
		return o1.get(0).compareTo(o2.get(0));
	}
	
}
