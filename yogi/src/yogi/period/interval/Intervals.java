package yogi.period.interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import yogi.base.util.range.Range;
import yogi.period.date.Date;
import yogi.period.date.Dates;
import yogi.period.date.range.DateRange;
import yogi.period.frequency.Frequencies;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyManager;

public class Intervals {
	private static IntervalMerger merger = new IntervalMerger();
	
	
	public static Interval union(Interval interval1, Interval interval2) {
		try {
			interval1.normalize();
		} catch (EmptyIntervalException e) {
			return interval2;
		}
		try {
			interval2.normalize();
		} catch (EmptyIntervalException e) {
			return interval1;
		}
		
		return unionWithoutNormalizing(interval1, interval2);
	}

	public static Interval unionWithoutNormalizing(Interval interval1, Interval interval2) {
		if(merger.isMergable(interval1, interval2))
			return merger.merge(interval1, interval2);
		
		throw new RuntimeException(String.format("Intervals:%1$s and %2$s can not be merged", interval1, interval2));
	}
	
		
	public static List<Interval> add(Interval toInterval, Interval interval) {
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(toInterval);
		return add(intervals, interval);
	}
	
	public static List<Interval> add(List<Interval> toIntervals, Interval interval)
	{
		List<Interval> rtnValue = new ArrayList<Interval>(toIntervals);
		rtnValue.addAll(subtract(interval, toIntervals));
		return rtnValue;
	}
	
	public static List<Interval> add(List<Interval> toIntervals, List<Interval> intervals)
	{
		List<Interval> rtnValue = new ArrayList<Interval>(toIntervals);
		for(Interval interval: intervals)
		{
			rtnValue.addAll(subtract(interval, rtnValue));
		}
		return rtnValue;
	}
	
	public static Interval intersection(Interval interval1, Interval interval2) {
		return new Interval(DateRange.convertToDateRange(interval1.getDateRange().intersection(interval2.getDateRange())), Frequencies.intersection(interval1.getFrequency(), interval2.getFrequency()));
	}
	
	public static List<Interval> intersection(List<Interval> intervals1, List<Interval> intervals2)
	{
		List<Interval> rtnValue = new ArrayList<Interval>();
		for(Interval interval1: intervals1)
		{
			for(Interval interval2: intervals2)
			{
				if(interval1.intersects(interval2))
				{
					rtnValue = add(rtnValue, intersection(interval1, interval2));
				}
			}
		}
		return rtnValue;
	}
	
	public static List<Interval> intersection(List<Interval> intervals, Interval interval)
	{
		List<Interval> rtnValue = new ArrayList<Interval>();
		for(Interval myInterval: intervals)
		{
				if(myInterval.intersects(interval))
				{
					rtnValue = add(rtnValue, intersection(myInterval, interval));
				}
		}
		return rtnValue;
	}
	
	public static List<Interval> subtract(Interval fromInterval, Interval interval) {
		List<Interval> rtnValue = new ArrayList<Interval>();
		if(!fromInterval.intersects(interval))
	    {
			rtnValue.add(fromInterval);
			return rtnValue;
	    }
		List<Range<Date>> difference = fromInterval.getDateRange().subtract(interval.getDateRange());
		for(Range<Date> dateRange: difference)
		{
			if(dateRange.isEmpty()) continue;
			rtnValue.add(new Interval(DateRange.convertToDateRange(dateRange), fromInterval.getFrequency()));
		}
		Frequency subtractFrequency = Frequencies.subtract(fromInterval.getFrequency(), interval.getFrequency());
		Range<Date> intersection = fromInterval.getDateRange().intersection(interval.getDateRange());
		if(!subtractFrequency.equals(FrequencyManager.NoDayFrequency)) rtnValue.add(new Interval(DateRange.convertToDateRange(intersection), subtractFrequency));
		return rtnValue;
	}
	
	public static List<Interval> subtract(List<Interval> fromIntervals, List<Interval> intervals) {
		List<Interval> rtnValue = fromIntervals;
		for(Interval interval: intervals)
		{
			rtnValue = subtract(rtnValue, interval);
		}
		return rtnValue;
	}
	
	public static List<Interval> subtract(Interval fromInterval, List<Interval> intervals) {
		List<Interval> rtnValue = new ArrayList<Interval>();
		rtnValue.add(fromInterval);
		return subtract(rtnValue, intervals);
	}
	
	public static List<Interval> subtract(List<Interval> fromIntervals, Interval interval) {
		List<Interval> rtnValue = new ArrayList<Interval>();
		for(Interval fromInterval: fromIntervals)
		{
			rtnValue.addAll(subtract(fromInterval, interval));
		}
		return rtnValue;
	}
	
	public static List<Interval> compress(List<Interval> intervals)
	{
		for(Iterator<Interval> iter = intervals.iterator(); iter.hasNext();)
		{
			Interval interval = iter.next();
			try {
				interval.normalize();
			} catch (EmptyIntervalException e) {
				iter.remove();
			}
		}
				
		return compressWithOutNormalizing(intervals);
	}
	
	public static List<Interval> compressWithOutNormalizing(List<Interval> intervals)
	{
		List<Interval> mergedIntervals = IntervalMergeManager.merge(intervals);
		return mergedIntervals;
	}
	
	public static List<Date> getDays(Interval interval)
	{
		List<Date> rtnValue = new ArrayList<Date>();
		Date startDate = interval.getDateRange().getStart();
		Date endDate = interval.getDateRange().getEnd();
		Frequency frequency = interval.getFrequency();
		for(int i = 0; i < 7; i ++)
		{
			Date currentDate = Dates.addDays(startDate, i);
			Frequency currentFrequency = FrequencyManager.get().getFrequency(currentDate);
			if(!Frequencies.intersects(frequency, currentFrequency)) continue;
			while(currentDate.getValue() <= endDate.getValue())
			{
				rtnValue.add(currentDate);
				currentDate = Dates.addDays(currentDate, 7);
			}
		}
		return rtnValue;
	}
	
	public static List<Date> getDaysSorted(Interval interval)
	{
		List<Date> rtnValue = getDays(interval);
		Collections.sort(rtnValue);
		return rtnValue;
	}
	
		
	public static Interval shift(Interval interval, int numberOfDays)
	{
		return new Interval(interval.getDateRange().shift(numberOfDays), 
				Frequencies.shift(interval.getFrequency(), numberOfDays));
	}
	
	public static List<Interval> shift(List<Interval> intervals, int numberOfDays)
	{
		List<Interval> rtnValue = new ArrayList<Interval>(intervals.size());
		for(Interval interval: intervals)
		{
			rtnValue.add(shift(interval, numberOfDays));
		}
		return rtnValue;
	}
	
	public static List<Interval> breakInterval(Interval interval, Interval matchInterval)
	{
		List<Interval> rtnValue = Intervals.subtract(interval, matchInterval);
		if(interval.intersects(matchInterval))
		{
			rtnValue.add(Intervals.intersection(interval, matchInterval));
		}
		return rtnValue;
	}
	
	public static List<Interval> breakIntervals(List<Interval> intervals, List<Interval> matchIntervals)
	{
		List<Interval> rtnValue = Intervals.subtract(intervals, matchIntervals);
		rtnValue.addAll(Intervals.intersection(intervals, matchIntervals));
		return rtnValue;
	}
		
	public static boolean same(Interval interval, List<Interval> otherIntervals)
	{
		List<Date> days = getDaysSorted(interval);
		List<Date> otherDays = getDaysSorted(otherIntervals);
		return checkSame(days, otherDays);
	}
	
	public static boolean same(List<Interval> intervals, List<Interval> otherIntervals)
	{
		List<Date> days = getDaysSorted(intervals);
		List<Date> otherDays = getDaysSorted(otherIntervals);
		return checkSame(days, otherDays);
	}

	public static boolean contains(List<Interval> intervals, List<Interval> otherIntervals)
	{
		return Intervals.same(otherIntervals, Intervals.intersection(intervals, otherIntervals));
	}

	public static boolean contains(List<Interval> intervals, Interval interval)
	{
		return Intervals.same(interval, Intervals.intersection(intervals, interval));
	}

	private static boolean checkSame(List<Date> days, List<Date> otherDays) {
		if(days.size() != otherDays.size()) return false;
		for(int i = 0; i < days.size(); i ++)
		{
			if(days.get(i) != otherDays.get(i)) return false;
		}
		return true;
	}

	public static List<Date> getDaysSorted(List<Interval> intervals) {
		List<Date> rtnValue = getDays(intervals);
		Collections.sort(rtnValue);
		return rtnValue;
	}
	
	public static List<Date> getDays(List<Interval> intervals) {
		List<Date> days = new ArrayList<Date>();
		for(Interval interval: intervals)
		{
			days.addAll(getDays(interval));
		}
		return days;
	}
	
	public static long getNumberOfDays(Interval interval)
	{
		Date startDate = interval.getDateRange().getStart();
		Frequency frequency = interval.getFrequency();
		long numberOfDays = interval.getDateRange().getNumberOfDays();
		long numberOfFullWeeks = numberOfDays/7;
		long partialWeekDays = numberOfDays%7;
		long rtnValue = numberOfFullWeeks * Frequencies.getNumberOfDays(frequency);
		for(int i = 0; i < partialWeekDays; i ++)
		{
			Date currentDate = Dates.addDays(startDate, i);
			Frequency currentFrequency = FrequencyManager.get().getFrequency(currentDate);
			if(!Frequencies.intersects(frequency, currentFrequency)) continue;
			rtnValue ++;
		}
		return rtnValue;
	}

	public static long getNumberOfDays(List<Interval> intervals)
	{
		long rtnValue = 0;
		for(Interval interval: intervals)
		{
			rtnValue = rtnValue + Intervals.getNumberOfDays(interval);
		}
		return rtnValue;
	}
	
	public static long getNumberOfDaysIntersecting(Interval interval, Interval intersectingInterval)
	{
		Interval intersection = Intervals.intersection(interval, intersectingInterval);
		return getNumberOfDays(intersection);
	}
	
	public static long getNumberOfDaysIntersecting(List<Interval> intervals, Interval intersectingInterval)
	{
		List<Interval> intersection = Intervals.intersection(intervals, intersectingInterval);
		return getNumberOfDays(intersection);
	}

	
	public static List<Interval> orderlyCompress(List<Interval> intervals)
	{
		List<Interval> rtnValue = orderlyCompressBase(intervals);
		return compress(rtnValue);
	}

	public static List<Interval> orderlyCompressWithoutNormalizing(List<Interval> intervals)
	{
		List<Interval> rtnValue = orderlyCompressBase(intervals);
		return compressWithOutNormalizing(rtnValue);
	}

	private static List<Interval> orderlyCompressBase(List<Interval> intervals) {
		Collections.sort(intervals, new Comparator<Interval>(){

			public int compare(Interval o1, Interval o2) {
				return o1.getDateRange().getStart().compareTo(o2.getDateRange().getStart());
			}
			
		});
		List<Interval> intervals1 = new ArrayList<Interval>(intervals);
		Collections.sort(intervals1, new Comparator<Interval>(){

			public int compare(Interval o1, Interval o2) {
				return o1.getDateRange().getEnd().compareTo(o2.getDateRange().getEnd());
			}
			
		});
		List<Interval> openIntervals = new ArrayList<Interval>();
		List<Interval> rtnValue = new ArrayList<Interval>();
		Date start = null;
		Iterator<Interval> iter = intervals1.iterator();
		Interval interval1 = iter.next();
		Date end = interval1.getDateRange().getEnd();
		for(Interval interval: intervals)
		{
			Date currentDate = interval.getDateRange().getStart();
			while(end.compareTo(currentDate) < 0 && start != null && start.compareTo(end) <= 0)
			{
				createInterval(openIntervals, rtnValue, start, end);
				openIntervals.remove(interval1);
				start = Dates.addDays(end, 1);
				interval1 = iter.next();
				end = interval1.getDateRange().getEnd();
				while(end.compareTo(start) < 0)
				{
					openIntervals.remove(interval1);
					interval1 = iter.next();
					end = interval1.getDateRange().getEnd();
				}
			}
			if(start != null && start.compareTo(currentDate) < 0 && !openIntervals.isEmpty())
			{
				createInterval(openIntervals, rtnValue, start, Dates.addDays(currentDate, -1));
			}
			start = currentDate;
			openIntervals.add(interval);
		}
		while(!openIntervals.isEmpty())
		{
			createInterval(openIntervals, rtnValue, start, end);
			openIntervals.remove(interval1);
			start = Dates.addDays(end, 1);
			if(iter.hasNext())
			{
				interval1 = iter.next();
				end = interval1.getDateRange().getEnd();
				while(end.compareTo(start) < 0 && !openIntervals.isEmpty())
				{
					openIntervals.remove(interval1);
					if(iter.hasNext())
					{
						interval1 = iter.next();
						end = interval1.getDateRange().getEnd();
					}
				}
			}
		}
		if(!openIntervals.isEmpty()) throw new RuntimeException("Error in the Algorithm");
		return rtnValue;
	}
	
	private static void createInterval(List<Interval> openIntervals, List<Interval> rtnValue, Date start, Date end) {
		Frequency frequency = FrequencyManager.NoDayFrequency;
		for(Interval openInteral: openIntervals)
		{
			frequency = Frequencies.add(frequency, openInteral.getFrequency());
		}
		rtnValue.add(new Interval(start, end, frequency));
	}
	
	public static void areIntervalsEqual(List<Interval> intervals, List<Interval> intervals1) throws Exception{
		HashSet<Date> daysBefore = new HashSet<Date>(Intervals.getDaysSorted(intervals));
		HashSet<Date> daysAfter = new HashSet<Date>(Intervals.getDaysSorted(intervals1));
		Date[][] dateDifferences = getDates(daysBefore, daysAfter);
		if(daysBefore.size() != daysAfter.size())
		{
			new Exception("Interval lists are not Equal: Mismatch in dates" + dateDifferences);
		}
		for(int i = 0; i < daysBefore.size(); i ++)
		{
			if(dateDifferences[i][0] != dateDifferences[i][1])
			{
				new Exception("Interval lists are not Equal: Mismatch in dates" + dateDifferences);
			}
		}
	}

	private static Date[][] getDates(HashSet<Date> daysBefore, HashSet<Date> daysAfter) {
		int size = daysBefore.size();
		if(daysAfter.size() > daysBefore.size()) size = daysAfter.size();
		ArrayList<Date> beforeDate = new ArrayList<Date>(daysBefore);
		ArrayList<Date> afterDate = new ArrayList<Date>(daysAfter);
		Collections.sort(beforeDate);
		Collections.sort(afterDate);
		Date[][] dateDifferences = new Date[size][2];
		for(int i = 0; i < beforeDate.size(); i ++)
		{
			dateDifferences[i][0] = beforeDate.get(i);
		}
		for(int i = 0; i < afterDate.size(); i ++)
		{
			dateDifferences[i][1] = afterDate.get(i);
		}
		return dateDifferences;
	}
}
