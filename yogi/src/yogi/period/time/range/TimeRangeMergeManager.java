package yogi.period.time.range;

import java.util.Comparator;
import java.util.List;

import yogi.base.util.merge.MergeManager;

class TimeRangeMergeManager  {

	private static TimeRangeMerger timeRangeMerger = new TimeRangeMerger();
	
	public static List<TimeRange> merge(List<TimeRange> dateRanges) 
	{		
		return MergeManager.merge(dateRanges, timeRangeMerger, new Comparator<TimeRange>(){

			@Override
			public int compare(TimeRange timeRange1, TimeRange timeRange2) {
				int result = timeRange1.getStartTime().compareTo(timeRange2.getStartTime());
				if(result != 0) {
					return result;
				}
				return timeRange1.getEndTime().compareTo(timeRange2.getEndTime());
			}
			
		});
	}
}
