package yogi.period.time.range;

import java.util.ArrayList;
import java.util.List;

import yogi.base.util.range.MultiRange;
import yogi.base.util.range.Range;
import yogi.period.time.Time;
import yogi.period.time.TimeManager;
import yogi.period.time.Times;
import yogi.period.time.io.HHMMTimeFormatter;
import yogi.period.time.range.io.TimeRangeFormatter;
import yogi.period.time.range.io.TimeRangeScanner;

public class TimeRange extends MultiRange<Time> implements Comparable<TimeRange>{

	private static TimeRangeFormatter timeRangeFormatter = new TimeRangeFormatter(new HHMMTimeFormatter(), ' ');
	public static TimeRange BLANK = new TimeRange(TimeManager.MinTime, TimeManager.MinTime);
	public static TimeRange ALL_DAY = new TimeRange(TimeManager.get().getTime(1), TimeManager.MaxTime);
		
	private boolean isStartTimeIncluded;
	private boolean isEndTimeIncluded;
	
	public TimeRange(int startTime, int endTime) {
		this(startTime, true, endTime, true);
	}
	
	public TimeRange(int startTime, boolean isStartTimeIncluded, int endTime, boolean isEndTimeIncluded) {
		this(TimeManager.get().getTime(startTime), isStartTimeIncluded, TimeManager.get().getTime(endTime), isEndTimeIncluded);
	}
		
	public TimeRange(Time startTime, boolean isStartTimeIncluded, Time endTime, boolean isEndTimeIncluded) {
		super();
		this.isStartTimeIncluded = isStartTimeIncluded;
		this.isEndTimeIncluded = isEndTimeIncluded;
		endTime = adjustMidNight(endTime);
		startTime = adjustMidNight(startTime);
		int diff = startTime.compareTo(endTime);
		if(diff <= 0){
			this.addRange(new Range<Time>(startTime, isStartTimeIncluded, endTime, isEndTimeIncluded));
		}else
		{
			this.addRange(new Range<Time>(startTime, isStartTimeIncluded, TimeManager.MaxTime, true));
			this.addRange(new Range<Time>(TimeManager.MinTime, true, endTime, isEndTimeIncluded));			
		}
	}

	public TimeRange(Range<Time> range) {
		this(range.getStart(), range.isStartIncluded(), range.getEnd(), range.isEndIncluded());
	}
	
	private Time adjustMidNight(Time time) {
		if( time == TimeManager.MaxTime ) {
			time = TimeManager.MinTime;
		}
		return time;
	}

	public TimeRange(Time startTime, Time endTime) {
		this(startTime, true, endTime, true);
	}

	public Time getEndTime() {
		return ranges.get(ranges.size()-1).getEnd();
	}

	public Time getStartTime() {
		return ranges.get(0).getStart();
	}
	
	public Time getTime()
	{
		return Times.subtract(getEndTime(), getStartTime());
	}
	
	public boolean isOverMidnight() {
		if (ranges.size() == 2) return true;
		return false;
	}
	/**
	 * This method is for usage of TimeRange in properties
	 * @param hhmmDashhhmm
	 * @return TimeRange
	 */
	public static TimeRange parse(String hhmmDashhhmm)
	{
		return new TimeRangeScanner('-').scan(hhmmDashhhmm);
	}
	
	@Override
	public boolean contains(Time value) {
		return super.contains(adjustMidNight(value));
	}

	@Override
	public String toString() {
		return timeRangeFormatter.format(this);
	}
	
	public boolean isContigous(TimeRange timeRange) {
		return Times.isContigous(this.getStartTime(), timeRange.getEndTime()) || 
			   Times.isContigous(this.getEndTime(), timeRange.getStartTime());
	}
	
	public static TimeRange convertToTimeRange(Range<Time> timeRange)
	{
		return new TimeRange(timeRange);
	}

	public static List<TimeRange> convertToTimeRanges(List<Range<Time>> timeRanges)
	{
		List<TimeRange> rtnValue = new ArrayList<TimeRange>(timeRanges.size());
		for(Range<Time> timeRange: timeRanges) {
			if(!timeRange.isEmpty()) {
				rtnValue.add(convertToTimeRange(timeRange));
			}
		}
		return rtnValue;
	}
	
	public long getNumberOfMinutes()
	{
		return Times.getDistance(this.getEndTime(), this.getStartTime()) + 1;
	}
	
	public Range<Time> intersection(TimeRange range) {
		if (isEmpty()) {
			Time temp = this.getStartTime();
			if (temp == null)
				temp = this.getEndTime();
			return new Range<Time>(temp, false, temp, false);
		}
		if (range.isEmpty()) {
			Time temp = range.getStartTime();
			if (temp == null)
				temp = range.getEndTime();
			return new Range<Time>(temp, false, temp, false);
		} else {
			boolean containMin = !isOverLowerBound(range.getStartTime());
			boolean containMax = !isUnderUpperBound(range.getEndTime());
			Time startValue = containMin ? this.getStartTime() : range.getStartTime();
			Time endValue = containMax ? this.getEndTime() : range.getEndTime();
			boolean isStartIncluded = containMin ? this.isStartTimeIncluded : range.isStartTimeIncluded;
			boolean isEndIncluded = containMax ? this.isEndTimeIncluded : range.isEndTimeIncluded;
			return new Range<Time>(startValue, isStartIncluded, endValue, isEndIncluded);
		}
	}
	
	private boolean isUnderUpperBound(Time value) {
		Time endValue = this.getEndTime();
		if (endValue == null)
			return true;
		if (value == null)
			return false;
		if (isEndTimeIncluded)
			return endValue.compareTo(value) >= 0;
		else
			return endValue.compareTo(value) > 0;
	}

	private boolean isOverLowerBound(Time value) {
		Time startValue = this.getStartTime();
		if (startValue == null)
			return true;
		if (value == null)
			return false;
		if (isStartTimeIncluded)
			return startValue.compareTo(value) <= 0;
		else
			return startValue.compareTo(value) < 0;
	}

	@Override
	public int compareTo(TimeRange timeRange) {
		int result = this.getStartTime().compareTo(timeRange.getStartTime());
		if(result != 0)
			return result;
		
		return this.getEndTime().compareTo(timeRange.getEndTime());
	}
}
