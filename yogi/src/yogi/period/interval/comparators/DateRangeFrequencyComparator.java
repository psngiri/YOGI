package yogi.period.interval.comparators;

import yogi.base.comparators.AndComparator;
import yogi.period.interval.Interval;

public class DateRangeFrequencyComparator extends AndComparator<Interval> {

	public DateRangeFrequencyComparator() {
		super(new IntervalDateRangeComparator(), new IntervalFrequencyComparator());
	}

}
