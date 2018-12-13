package yogi.period.time.range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yogi.base.util.immutable.ImmutableList;
import yogi.period.time.Time;

public class TimeRanges {
	
	public static final ImmutableList<TimeRange> EMPTY_IMMUTABLE_LIST = new ImmutableList<TimeRange>(new ArrayList<TimeRange>(0));
	public static final List<TimeRange> ALL_DAY_LIST = Arrays.asList(new TimeRange[]{TimeRange.ALL_DAY});
	
	public static List<TimeRange> compress(List<TimeRange> timeRanges) {
		return TimeRangeMergeManager.merge(timeRanges);
	}
	
	public static List<TimeRange> add(List<TimeRange> toTimeRanges, List<TimeRange> timeRanges) {
		List<TimeRange> rtnValue = new ArrayList<TimeRange>(toTimeRanges);
		for(TimeRange timeRange: timeRanges)
		{
			rtnValue.addAll(subtract(timeRange, rtnValue));
		}
		return rtnValue;
	}
	
	public static List<TimeRange> add(List<TimeRange> toTimeRanges, TimeRange timeRange) {
		List<TimeRange> rtnValue = new ArrayList<TimeRange>(toTimeRanges);
		rtnValue.addAll(subtract(timeRange, toTimeRanges));
		return rtnValue;
	}
	
	public static List<TimeRange> subtract(TimeRange fromTimeRange, List<TimeRange> timeRanges) {
		List<TimeRange> rtnValue = new ArrayList<TimeRange>();
		rtnValue.add(fromTimeRange);
		return subtract(rtnValue, timeRanges);
	}
	
	public static List<TimeRange> subtract(List<TimeRange> fromTimeRanges, TimeRange timeRange) {
		List<TimeRange> rtnValue = new ArrayList<TimeRange>();
		for(TimeRange fromTimeRange: fromTimeRanges) {
			rtnValue.addAll(TimeRange.convertToTimeRanges(fromTimeRange.subtract(timeRange)));
		}
		return rtnValue;
	}

	public static List<TimeRange> subtract(List<TimeRange> fromTimeRanges, List<TimeRange> timeRanges) {
		List<TimeRange> rtnValue = fromTimeRanges;
		for(TimeRange timeRange: timeRanges) {
			rtnValue = subtract(rtnValue, timeRange);
		}
		return rtnValue;
	}
	
	public static List<TimeRange> intersection(List<TimeRange> timeRanges, TimeRange timeRange) {
		List<TimeRange> rtnValue = new ArrayList<TimeRange>();
		for(TimeRange myTimeRange: timeRanges)
		{
				if(myTimeRange.intersects(timeRange))
				{
					rtnValue = add(rtnValue, TimeRange.convertToTimeRange(myTimeRange.intersection(timeRange)));
				}
		}
		return rtnValue;
	}
	
	public static List<TimeRange> intersection(List<TimeRange> timeRanges1, List<TimeRange> timeRanges2)
	{
		List<TimeRange> rtnValue = new ArrayList<TimeRange>();
		for(TimeRange timeRange1: timeRanges1)
		{
			for(TimeRange timeRange2: timeRanges2)
			{
				if(timeRange1.intersects(timeRange2))
				{
					rtnValue = add(rtnValue, TimeRange.convertToTimeRange(timeRange1.intersection(timeRange2)));
				}
			}
		}
		return rtnValue;
	}
	
	public static boolean intersects(List<TimeRange> timeRanges, TimeRange timeRange)
	{
		for(TimeRange timeRange1: timeRanges)
		{
			if(timeRange1.intersects(timeRange))
			{
				return true;
			}
		}
		return false;
	}

	public static boolean intersects(List<TimeRange> timeRanges1, List<TimeRange> timeRanges2)
	{
		for(TimeRange timeRange1: timeRanges1)
		{
			for(TimeRange timeRange2: timeRanges2)
			{
				if(timeRange1.intersects(timeRange2))
				{
					return true;
				}
			}
		}
		return false;
	}

	public static boolean contains(List<TimeRange> timeRanges, TimeRange timeRange) {
		List<TimeRange> ranges = TimeRanges.intersection(timeRanges, timeRange);
		return TimeRanges.subtract(timeRange, ranges).isEmpty();

	}
	
	public static boolean contains(List<TimeRange> timeRanges, Time time) {
		for (TimeRange timeRange : timeRanges) {
			if(timeRange.contains(time)) return true;
		}
		return false;
	}
	
	public static boolean contains(List<TimeRange> timeRanges, List<TimeRange> containsTimeRanges) {
		List<TimeRange> ranges = TimeRanges.intersection(timeRanges, containsTimeRanges);
		return TimeRanges.subtract(containsTimeRanges, ranges).isEmpty();

	}
	
	public static int compare(List<TimeRange> timeRanges1, List<TimeRange> timeRanges2) {
		if(timeRanges1.isEmpty() && timeRanges2.isEmpty()) {
			return 0;
		} else if(timeRanges1.isEmpty()) {
			return -1;
		} else if(timeRanges2.isEmpty()) {
			return 1;
		}
		compress(timeRanges1);
		compress(timeRanges2);
		int size1 = timeRanges1.size();
		int size2 = timeRanges2.size();
		int min = Math.min(size1, size2);
		int i = 0;
		for(; i < min; i++) {
			int rtnVal = timeRanges1.get(i).compareTo(timeRanges2.get(i));
			if(rtnVal != 0) {
				if(rtnVal < 0) {
					return -1;
				} else {
					return 1;
				}
			}
		}
		if(size1 != size2) {
			if(i == size1) return -1;
			if(i == size2) return 1;
		}
		return 0;
	}
}
