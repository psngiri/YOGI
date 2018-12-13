package yogi.period.date.range;

import yogi.base.util.merge.Merger;
import yogi.period.date.Date;

class DateRangeMerger implements Merger<DateRange> {

	public boolean isMergable(DateRange dateRange1, DateRange dateRange2) {
		if(dateRange1 == null || dateRange2 == null) 
			return false;
			if(dateRange1.intersects(dateRange2) 
			  || dateRange1.isContigous(dateRange2))
			{
				return true;
			}
		
		if(dateRange1.equals(dateRange2))
		{
			return true;
		}
		return false;
	}

	@Override
	public DateRange merge(DateRange dateRange1, DateRange dateRange2) {
		Date startDate = dateRange1.getStart();
		Date endDate = dateRange2.getEnd();
		if(dateRange1.getStart().getValue() > dateRange2.getStart().getValue())
		{
			startDate = dateRange2.getStart();
		}
		if(dateRange1.getEnd().getValue() > dateRange2.getEnd().getValue())
		{
			endDate = dateRange1.getEnd();
		}
		DateRange dateRange = new DateRange(startDate, endDate);
		return dateRange;
	}


	
}
