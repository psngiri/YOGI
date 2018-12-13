package yogi.report.dated.columns.week;

import java.util.ArrayList;
import java.util.List;

import yogi.period.date.Date;
import yogi.period.date.Dates;
import yogi.period.interval.Interval;
import yogi.report.dated.IntervalSplitter;

public abstract class WeekSplitter implements IntervalSplitter {

	public List<Interval> split(Interval interval) {
		List<Interval> rtnValue = new ArrayList<Interval>();
		Date start = interval.getDateRange().getStart();
		Date end = interval.getDateRange().getEnd();
		int startDayOfWeek = getDayOfWeek(start);
		int shift = startDayOfWeek -1;
		Date curStartDate = start;
		Date curEndDate = Dates.addDays(curStartDate, 6 - shift);

		if(shift != 0)
		{
			if(curEndDate.getValue() > end.getValue()) curEndDate = end;
			rtnValue.add(new Interval(curStartDate, curEndDate, interval.getFrequency()));
			curStartDate = Dates.addDays(start, 7 - shift);
		}
		while(curEndDate.getValue() < end.getValue())
		{
			curEndDate = Dates.addDays(curStartDate, 6);
			if(curEndDate.getValue() > end.getValue()) curEndDate = end;
			rtnValue.add(new Interval(curStartDate, curEndDate, interval.getFrequency()));
			curStartDate = Dates.addDays(curStartDate, 7);
		}
		return rtnValue;
	}

	public abstract int getDayOfWeek(Date start);

}
