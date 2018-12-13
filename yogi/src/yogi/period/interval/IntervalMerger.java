package yogi.period.interval;

import yogi.base.util.merge.Merger;
import yogi.period.date.Date;
import yogi.period.date.Dates;
import yogi.period.date.range.DateRange;
import yogi.period.frequency.Frequencies;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyManager;

class IntervalMerger implements Merger<Interval> {

	public boolean isMergable(Interval interval1, Interval interval2) {
		if(interval1 == null || interval2 == null) 
			return false;
		DateRange dateRange1 = interval1.getDateRange();
		DateRange dateRange2 = interval2.getDateRange();
		if(interval1.getFrequency().equals(interval2.getFrequency()))
		{
			if(dateRange1.intersects(dateRange2) 
			  || dateRange1.isContigous(dateRange2))
			{
				return true;
			}
			try {
				if(isContigous(interval1, interval2))
				{
					return true;
				}
			} catch (EmptyIntervalException e) {
				return true;
			}
		}
		
		if(dateRange1.equals(dateRange2))
		{
			return true;
		}
		try {
			if(isAdjacent(interval1, interval2))
			{
				return true;
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(interval1.contains(interval2))
		{
			return true;
		}
		if(interval2.contains(interval1))
		{
			return true;
		}
		if(!Frequencies.intersects(interval1.getFrequency(), interval2.getFrequency()))
		{
			if(dateRange1.intersects(dateRange2))
			{
				if(dateRange1.contains(dateRange2))
				{
					return checkNoOperationalFrequency(new DateRange(dateRange1.getStart(), true, dateRange2.getStart(), false), interval1.getFrequency(), interval2.getFrequency()) &&
					checkNoOperationalFrequency(new DateRange(dateRange2.getEnd(), false, dateRange1.getEnd(), true), interval1.getFrequency(), interval2.getFrequency());
				}else if(dateRange2.contains(dateRange1)){
					return checkNoOperationalFrequency(new DateRange(dateRange2.getStart(), true, dateRange1.getStart(), false), interval2.getFrequency(), interval1.getFrequency()) &&
					checkNoOperationalFrequency(new DateRange(dateRange1.getEnd(), false, dateRange2.getEnd(), true), interval2.getFrequency(), interval1.getFrequency());
				}else
				{
					return checkNoOperationalFrequencies(getNonIntersectingInterval(interval1, interval2), getNonIntersectingInterval(interval2, interval1));
				}
			}
		}
		return false;
	}

	private Interval getNonIntersectingInterval(Interval fromInterval, Interval otherInterval) 
	{
		DateRange fromDateRange = fromInterval.getDateRange();
		Date otherStartDate = otherInterval.getDateRange().getStart();
		DateRange nonIntersectingDateRange = null;
		if(fromDateRange.contains(otherStartDate))
		{
			nonIntersectingDateRange = new DateRange(fromDateRange.getStart(), true, otherStartDate, false);
		}
		Date otherEndDate = otherInterval.getDateRange().getEnd();
		if(fromDateRange.contains(otherEndDate))
		{
			nonIntersectingDateRange = new DateRange(otherEndDate, false, fromDateRange.getEnd(), true);
		}
		return new Interval(nonIntersectingDateRange, fromInterval.getFrequency());
	}

	public Interval merge(Interval interval1, Interval interval2) {
		if(interval1.contains(interval2))
		{
			return interval1;
		}
		if(interval2.contains(interval1))
		{
			return interval2;
		}
		return new Interval(getDateRange(interval1, interval2), 
				   Frequencies.add(interval1.getFrequency(), interval2.getFrequency()));
		
	}

	private static DateRange getDateRange(Interval interval1, Interval interval2) {
		Date startDate = interval1.getDateRange().getStart();
		Date endDate = interval2.getDateRange().getEnd();
		if(interval1.getDateRange().getStart().getValue() > interval2.getDateRange().getStart().getValue())
		{
			startDate = interval2.getDateRange().getStart();
		}
		if(interval1.getDateRange().getEnd().getValue() > interval2.getDateRange().getEnd().getValue())
		{
			endDate = interval1.getDateRange().getEnd();
		}
		DateRange dateRange = new DateRange(startDate, endDate);
		return dateRange;
	}
	
	public static boolean isContigous(Interval interval1, Interval interval2) throws EmptyIntervalException
	{
		if(!interval1.before(interval2))
		{
			return isContigous(interval2, interval1);
		}
		
		Date date1 = interval1.getDateRange().getEnd();
		long distance = Dates.getDistance(interval2.getDateRange().getStart(), date1);
		if(distance <= 7)
		{
			Frequency union = Frequencies.add(interval1.getFrequency(), interval2.getFrequency());
			Frequency frequency = FrequencyManager.get().getFrequency(date1);
			
			for(int i=1; i<distance; i++)
			{
				frequency = Frequencies.shift(frequency, 1);
				if(Frequencies.intersects(union, frequency))
					return false;
			}
					
			return true;
		}
		return false;
	}
	
	public static boolean isAdjacent(Interval interval1, Interval interval2)
	{
		if(!interval1.before(interval2))
		{
			return isAdjacent(interval2, interval1);
		}
	
		try {
			if(!isContigous(interval1, interval2))		
			{
				return false;
			}
		} catch (EmptyIntervalException e) {
			return true;
		}
		
		if(Dates.getDistance(interval2.getDateRange().getEnd(), interval1.getDateRange().getStart()) < 8)
		{
			return true;
		}
		
		return checkNoAdditionalFrequencies(interval1, interval2);
	}

	private static boolean checkNoAdditionalFrequencies(Interval interval1, Interval interval2) 
	{
		if(probableNoAdditionalFrequencies(interval1, interval2))
		{
			return checkNoOperationalFrequencies(interval1, interval2);
		}
		return false;
	}

	private static boolean checkNoOperationalFrequencies(Interval interval1, Interval interval2) {
		if(checkNoOperationalFrequency(interval1.getDateRange(), interval1.getFrequency(), interval2.getFrequency())
			&& checkNoOperationalFrequency(interval2.getDateRange(), interval2.getFrequency(), interval1.getFrequency()))
		{
			return true;
		}
		return false;
	}
	
	private static boolean checkNoOperationalFrequency(DateRange dateRange, Frequency operationalFrequency, Frequency nonOpFrequency)
	{
		Frequency frequency = Frequencies.getOperationalFrequency(dateRange,nonOpFrequency);
		if(Frequencies.isSubSet(operationalFrequency, frequency))
			return true;
		return false;
	}

	private static boolean probableNoAdditionalFrequencies(Interval interval1, Interval interval2) 
	{
		if(interval1.getDateRange().getNumberOfDays() >= interval2.getDateRange().getNumberOfDays())
		{
			if(interval2.getDateRange().getNumberOfDays() < 7 &&
			   Frequencies.isSubSet(interval1.getFrequency(), interval2.getFrequency()))
			{
				return true;
			}
		}
		else
		{
			return probableNoAdditionalFrequencies(interval2, interval1);
		}
		return false;
	}

	
}
