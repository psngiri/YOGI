package yogi.period.date.range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yogi.base.util.immutable.ImmutableList;
import yogi.period.date.Date;

public class DateRanges {
	
	public static final ImmutableList<DateRange> EMPTY_IMMUTABLE_LIST = new ImmutableList<DateRange>(new ArrayList<DateRange>(0));
	public static final List<DateRange> ALL_DATE_LIST = Arrays.asList(new DateRange[]{DateRange.ALL_DATE});
	
	public static List<DateRange> subtract(DateRange fromDateRange, List<DateRange> dateRanges) {
		List<DateRange> rtnValue = new ArrayList<DateRange>();
		rtnValue.add(fromDateRange);
		return subtract(rtnValue, dateRanges);
	}

	public static List<DateRange> add(List<DateRange> toDateRanges, List<DateRange> dateRanges) {
		List<DateRange> rtnValue = new ArrayList<DateRange>(toDateRanges);
		for(DateRange dateRange: dateRanges)
		{
			rtnValue.addAll(subtract(dateRange, rtnValue));
		}
		return rtnValue;
	}

	public static List<DateRange> compress(List<DateRange> dateRanges) {
		return DateRangeMergeManager.merge(dateRanges);
	}

	public static List<DateRange> subtract(List<DateRange> fromDateRanges, DateRange dateRange) {
		List<DateRange> rtnValue = new ArrayList<DateRange>();
		for(DateRange fromDateRange: fromDateRanges)
		{
			rtnValue.addAll(DateRange.convertToDateRanges(fromDateRange.subtract(dateRange)));
		}
		return rtnValue;
	}

	public static List<DateRange> subtract(List<DateRange> fromDateRanges, List<DateRange> dateRanges) {
		List<DateRange> rtnValue = fromDateRanges;
		for(DateRange dateRange: dateRanges)
		{
			rtnValue = subtract(rtnValue, dateRange);
		}
		return rtnValue;
	}

	public static List<DateRange> intersection(List<DateRange> dateRanges, DateRange dateRange) {
		List<DateRange> rtnValue = new ArrayList<DateRange>();
		for(DateRange myDateRange: dateRanges)
		{
				if(myDateRange.intersects(dateRange))
				{
					rtnValue = add(rtnValue, DateRange.convertToDateRange(myDateRange.intersection(dateRange)));
				}
		}
		return rtnValue;
	}
	
	public static List<DateRange> intersection(List<DateRange> dateRanges1, List<DateRange> dateRanges2)
	{
		List<DateRange> rtnValue = new ArrayList<DateRange>();
		for(DateRange dateRange1: dateRanges1)
		{
			for(DateRange dateRange2: dateRanges2)
			{
				if(dateRange1.intersects(dateRange2))
				{
					rtnValue = add(rtnValue, DateRange.convertToDateRange(dateRange1.intersection(dateRange2)));
				}
			}
		}
		return rtnValue;
	}
	
	public static boolean contains(List<DateRange> dateRanges, DateRange dateRange) {
		List<DateRange> ranges = DateRanges.intersection(dateRanges, dateRange);
		return DateRanges.subtract(dateRange, ranges).isEmpty();

	}
	public static boolean contains(List<DateRange> dateRanges, Date date) {
		for(DateRange dateRange: dateRanges){
			if(dateRange.contains(date)) return true;
		}
		return false;

	}
	
	public static boolean contains(List<DateRange> dateRanges, List<DateRange> containsDateRanges) {
		List<DateRange> ranges = DateRanges.intersection(dateRanges, containsDateRanges);
		return DateRanges.subtract(containsDateRanges, ranges).isEmpty();

	}
	
	public static List<DateRange> add(List<DateRange> toDateRanges,
			DateRange dateRange) {
		List<DateRange> rtnValue = new ArrayList<DateRange>(toDateRanges);
		rtnValue.addAll(subtract(dateRange, toDateRanges));
		return rtnValue;
	}

	public static boolean intersects(List<DateRange> dateRanges, DateRange dateRange)
	{
		for(DateRange dateRange1: dateRanges)
		{
			if(dateRange1.intersects(dateRange))
			{
				return true;
			}
		}
		return false;
	}

	public static boolean intersects(List<DateRange> dateRanges1, List<DateRange> dateRanges2)
	{
		for(DateRange dateRange1: dateRanges1)
		{
			for(DateRange dateRange2: dateRanges2)
			{
				if(dateRange1.intersects(dateRange2))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static int compare(List<DateRange> dateRanges1, List<DateRange> dateRanges2) {
		if(dateRanges1.isEmpty() && dateRanges2.isEmpty()) {
			return 0;
		} else if(dateRanges1.isEmpty()) {
			return -1;
		} else if(dateRanges2.isEmpty()) {
			return 1;
		}
		dateRanges1 = compress(dateRanges1);
		dateRanges2 = compress(dateRanges2);
		int size1 = dateRanges1.size();
		int size2 = dateRanges2.size();
		int min = Math.min(size1, size2);
		int i = 0;
		for(; i < min; i++) {
			int rtnVal = dateRanges1.get(i).compareTo(dateRanges2.get(i));
			if(rtnVal != 0) return rtnVal;
		}
		if(size1 != size2) {
			if(i == size1) return -1;
			if(i == size2) return 1;
		}
		return 0;
	}
	
	public static int difference(List<DateRange> dateRangeList1, List<DateRange> dateRangeList2) {
		if(dateRangeList1 == null || dateRangeList2 == null) return 0;
		List<DateRange> diffDateRanges1 = DateRanges.subtract(dateRangeList1, dateRangeList2);
		List<DateRange> diffDateRanges2 = DateRanges.subtract(dateRangeList2, dateRangeList1);
		List<DateRange> diffDateRanges = DateRanges.add(diffDateRanges1, diffDateRanges2);
		diffDateRanges = DateRanges.compress(diffDateRanges);
		int countOfDays = 0;
		for(DateRange diffDateRange : diffDateRanges) {			
			long numberOfDays = diffDateRange.getNumberOfDays();
			if(numberOfDays > countOfDays) countOfDays = (int)numberOfDays;
		}
		return countOfDays;	
	}
}
