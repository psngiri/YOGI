package yogi.period.interval;

import java.util.ArrayList;

import yogi.base.util.immutable.ImmutableList;
import yogi.period.date.Date;
import yogi.period.date.DateAssistant;
import yogi.period.date.range.DateRange;
import yogi.period.frequency.Frequencies;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyManager;

public class Interval implements Comparable<Interval>{
	public static final ImmutableList<Interval> EMPTY_IMMUTABLE_LIST = new ImmutableList<Interval>(new ArrayList<Interval>(0));
	DateRange dateRange;
	Frequency frequency;
	
	public Interval(DateRange dateRange, Frequency frequency) {
		super();
		this.dateRange = dateRange;
		this.frequency = frequency;
	}
	
	public Interval(Date start, Date end, Frequency frequency) {
	  this(new DateRange(start, end), frequency);
	}

	public Interval(Date date) {
		  this(new DateRange(date, date), FrequencyManager.get().getFrequency(date));
	}
	
	public void normalize() throws EmptyIntervalException
	{
		Date adjustedStartDate = DateAssistant.get().adjustDateForward(dateRange.getStart(), frequency);
		Date adjustedEndDate = DateAssistant.get().adjustDateBackward(dateRange.getEnd(), frequency);
		if(adjustedStartDate.getValue() > adjustedEndDate.getValue())
			throw new EmptyIntervalException(this);
		dateRange = new DateRange(adjustedStartDate, adjustedEndDate);
		frequency = DateAssistant.get().adjustFrequency(dateRange, frequency);
	}
	
	public DateRange getDateRange()
	{
		return dateRange;
	}

	public Frequency getFrequency()
	{
		return frequency;
	}
	
	public boolean contains(Date date) {
		if(!dateRange.contains(date)) return false;
		Frequency myFrequency = FrequencyManager.get().getFrequency(date);
		if(Frequencies.isSubSet(frequency, myFrequency)) return true;
		return false;
	}
	
	public boolean contains(Interval interval) {
		if(!dateRange.contains(interval.getDateRange())) return false;
		if(Frequencies.isSubSet(frequency, interval.getFrequency())) return true;
		return false;
	}
	
	public boolean intersects(Interval interval) {
		if(!dateRange.intersects(interval.getDateRange())) return false;
		if(Frequencies.intersects(frequency, interval.getFrequency())) return true;
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj instanceof Interval)
		{
			Interval interval = (Interval)obj;
			return interval.getDateRange().equals(getDateRange()) && interval.getFrequency().equals(getFrequency());
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(dateRange.toString());
		sb.append(' ');
		sb.append(frequency.toString());
		return sb.toString();
	}
	
	public boolean before(Interval interval1)
	{
		return (getDateRange().getStart().getValue() <= interval1.getDateRange().getStart().getValue());
	}

	public int compareTo(Interval interval) {
		int rtnValue = this.getDateRange().compareTo(interval.getDateRange());
		if(rtnValue != 0) return rtnValue;
		return this.getFrequency().compareTo(interval.getFrequency());
	}
	
}
