package yogi.period.time.range;

import yogi.base.util.merge.Merger;
import yogi.period.time.Time;

class TimeRangeMerger implements Merger<TimeRange> {

	public boolean isMergable(TimeRange timeRange1, TimeRange timeRange2) {
		if(timeRange1 == null || timeRange2 == null) 
			return false;
			if(timeRange1.intersects(timeRange2) || timeRange1.isContigous(timeRange2))
			{
				return true;
			}
		
		if(timeRange1.equals(timeRange2))
		{
			return true;
		}
		return false;
	}

	@Override
	public TimeRange merge(TimeRange timeRange1, TimeRange timeRange2) {
		Time startTime = timeRange1.getStartTime();
		Time endTime = timeRange2.getEndTime();
		if(timeRange1.getStartTime().getTime() > timeRange2.getStartTime().getTime())
		{
			startTime = timeRange2.getStartTime();
		}
		if(timeRange1.getEndTime().getTime() > timeRange2.getEndTime().getTime())
		{
			endTime = timeRange1.getEndTime();
		}
		TimeRange timeRange = new TimeRange(startTime, endTime);
		return timeRange;
	}
	
}
