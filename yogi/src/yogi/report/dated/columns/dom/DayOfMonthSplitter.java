package yogi.report.dated.columns.dom;

import java.util.ArrayList;
import java.util.List;

import yogi.period.date.Date;
import yogi.period.interval.Interval;
import yogi.period.interval.Intervals;
import yogi.report.dated.IntervalSplitter;

public class DayOfMonthSplitter implements IntervalSplitter {

	public List<Interval> split(Interval interval) {
		List<Interval> rtnValue = new ArrayList<Interval>();
		List<Date> days = Intervals.getDays(interval);
		for(Date date: days)
		{
			rtnValue.add(new Interval(date));
		}
		return rtnValue;
	}

}
