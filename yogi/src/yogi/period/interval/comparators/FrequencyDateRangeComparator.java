package yogi.period.interval.comparators;

import yogi.base.comparators.AndComparator;
import yogi.period.interval.Interval;

public class FrequencyDateRangeComparator extends AndComparator<Interval> {

	public FrequencyDateRangeComparator() {
		super(new IntervalFrequencyComparator(), new IntervalDateRangeComparator());
	}

}
