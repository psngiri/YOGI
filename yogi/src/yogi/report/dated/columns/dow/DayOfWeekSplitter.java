package yogi.report.dated.columns.dow;

import java.util.ArrayList;
import java.util.List;

import yogi.period.frequency.Frequencies;
import yogi.period.frequency.Frequency;
import yogi.period.interval.Interval;
import yogi.report.dated.IntervalSplitter;

public class DayOfWeekSplitter implements IntervalSplitter {

	public List<Interval> split(Interval interval) {
		List<Interval> rtnValue = new ArrayList<Interval>();
		for(Frequency frequency: Frequencies.split(interval.getFrequency()))
		{
			rtnValue.add(new Interval(interval.getDateRange(), frequency));
		}
		return rtnValue;
	}

}
