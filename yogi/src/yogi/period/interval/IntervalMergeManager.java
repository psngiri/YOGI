package yogi.period.interval;

import java.util.List;

import yogi.base.util.merge.MergeManager;
import yogi.period.interval.comparators.DateRangeFrequencyComparator;
import yogi.period.interval.comparators.FrequencyDateRangeComparator;

class IntervalMergeManager  {

	private static IntervalMerger intervalMerger = new IntervalMerger();
	
	public static List<Interval> merge(List<Interval> intervals) 
	{		
		List<Interval> mergedIntervals = MergeManager.merge(intervals, intervalMerger, new DateRangeFrequencyComparator());
		return MergeManager.merge(mergedIntervals, intervalMerger, new FrequencyDateRangeComparator());
	}
}
