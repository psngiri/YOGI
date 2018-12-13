/**
 * 
 */
package yogi.period.interval.comparators;

import java.util.Comparator;

import yogi.period.interval.Interval;

public class IntervalDateRangeComparator implements Comparator<Interval>
{

	public int compare(Interval interval1, Interval interval2) {
		return interval1.getDateRange().compareTo(interval2.getDateRange());
	}
	
}