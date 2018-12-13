package yogi.period.interval.data;

import java.util.ArrayList;
import java.util.List;

import yogi.base.util.immutable.ImmutableList;
import yogi.period.interval.Interval;
import yogi.period.interval.Intervals;

public class IntervalsObject {
	private List<Interval> intervals;
	public IntervalsObject() {
		this(new ArrayList<Interval>());
	}
	public IntervalsObject(List<Interval> intervals) {
		super();
		this.intervals = new ArrayList<Interval>(intervals);
	}

	public ImmutableList<Interval> getIntervals() {
		return new ImmutableList<Interval>(intervals);
	}
	
	protected void add(Interval interval)
	{
		List<Interval> newIntervals = Intervals.subtract(interval, intervals);
		intervals.addAll(newIntervals);
	}
	
	protected void add(List<Interval> intervals)
	{
		setIntervals(Intervals.add(this.intervals, intervals));
	}
	
	protected void compress() {
		intervals = Intervals.compress(intervals);
	}
	protected void orderlyCompress()
	{
		intervals = Intervals.orderlyCompress(intervals);
	}
	protected void delete(Interval interval)
	{
		setIntervals(Intervals.subtract(intervals, interval));
	}
	protected void delete(List<Interval> intervals)
	{
		setIntervals(Intervals.subtract(this.intervals, intervals));
	}
	
	public boolean isEmpty()
	{
		return intervals.isEmpty();
	}
	
	private void setIntervals(List<Interval> intervals)
	{
		this.intervals.clear();
		this.intervals.addAll(intervals);
	}
	@Override
	public String toString() {
		return intervals.toString();
	}
}
