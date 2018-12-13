package yogi.versioning;

import yogi.base.util.immutable.ImmutableList;
import yogi.period.interval.Interval;

public interface DatedData<T> {
	T getData();
	ImmutableList<Interval> getIntervals();
}
