package yogi.period.date.range;

import java.util.ArrayList;
import java.util.List;

import yogi.base.util.range.Range;
import yogi.period.date.Date;
import yogi.period.date.DateManager;
import yogi.period.date.Dates;

public class DateRange extends Range<Date> implements Comparable<DateRange>{

	private static final long serialVersionUID = 2980721499448776177L;

	public static final DateRange ALL_DATE = new DateRange(DateManager.EARLIEST_DATE, DateManager.INFINITY); 
	
	public DateRange(Date startDate, Date endDate) {
		this(startDate, true, endDate, true);
	}
	
	public DateRange(Date startDate, boolean isStartDateIncluded, Date endDate, boolean isEndDateIncluded) {
		super((isStartDateIncluded)?startDate:Dates.addDays(startDate, 1), true, (isEndDateIncluded)?endDate:Dates.subtractDays(endDate, 1), true);
		int diff = startDate.compareTo(endDate);
		if(diff > 0)
			throw new RuntimeException("Start date : " + startDate + " after end date : " + endDate);
	}

	public DateRange(Range<Date> range)
	{
		this(range.getStart(), range.isStartIncluded(), range.getEnd(), range.isEndIncluded());
	}
	
	public static DateRange convertToDateRange(Range<Date> dateRange)
	{
		return new DateRange(dateRange);
	}

	
	public DateRange intersectionDateRange(Range<Date> range) {
		return convertToDateRange(super.intersection(range));
	}

	public static List<DateRange> convertToDateRanges(List<Range<Date>> dateRanges)
	{
		List<DateRange> rtnValue = new ArrayList<DateRange>(dateRanges.size());
		for(Range<Date> dateRange: dateRanges)
		{
			if(!dateRange.isEmpty())
			{
				rtnValue.add(convertToDateRange(dateRange));
			}
		}
		return rtnValue;
	}

	public DateRange shift(int numberOfDays)
	{
		return new DateRange(Dates.addDays(getStart(), numberOfDays), Dates.addDays(getEnd(), numberOfDays));
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getStart().toString());
		sb.append(' ');
		sb.append(getEnd().toString());
		return sb.toString();
	}	
	
	public boolean isContigous(DateRange dateRange)
	{
		return Dates.isContigous(this.getStart(), dateRange.getEnd()) || 
			   Dates.isContigous(this.getEnd(), dateRange.getStart());
	}
	
	public long getNumberOfDays()
	{
		return Dates.getDistance(this.getEnd(), this.getStart()) + 1;
	}

	public int compareTo(DateRange dateRange) 
	{
		int result = this.getStart().compareTo(dateRange.getStart());
		if(result != 0)
			return result;
		
		return this.getEnd().compareTo(dateRange.getEnd());
	}
}
