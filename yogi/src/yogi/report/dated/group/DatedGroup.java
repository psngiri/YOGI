package yogi.report.dated.group;

import java.util.List;

import yogi.period.interval.Interval;
import yogi.report.group.Group;

public interface DatedGroup<T> extends Group<T>{
	List<Interval> getIntervals(int indexInGroup);
	boolean isValid();
}
