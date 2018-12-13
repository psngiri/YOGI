/**
 * 
 */
package yogi.period.interval.comparators;

import java.util.Comparator;

import yogi.period.interval.Interval;

public class IntervalFrequencyComparator implements Comparator<Interval>
{

	public int compare(Interval interval1, Interval interval2) {
		return interval1.getFrequency().compareTo(interval2.getFrequency());
	}
	
}