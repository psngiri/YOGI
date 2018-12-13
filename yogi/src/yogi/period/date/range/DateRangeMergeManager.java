package yogi.period.date.range;

import java.util.Comparator;
import java.util.List;

import yogi.base.util.merge.MergeManager;

class DateRangeMergeManager  {

	private static DateRangeMerger dateRangeMerger = new DateRangeMerger();
	
	public static List<DateRange> merge(List<DateRange> dateRanges) 
	{		
		return MergeManager.merge(dateRanges, dateRangeMerger, new Comparator<DateRange>(){

			@Override
			public int compare(DateRange dateRange1, DateRange dateRange2) {
				return dateRange1.compareTo(dateRange2);
			}
			
		});
	}
}
