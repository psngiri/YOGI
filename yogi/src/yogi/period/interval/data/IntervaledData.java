package yogi.period.interval.data;

import java.util.ArrayList;
import java.util.List;

import yogi.period.interval.Interval;

public class IntervaledData<T> extends IntervalsObject{
	private T data;
	public IntervaledData(T data) {
		this(data, new ArrayList<Interval>());
	}
	public IntervaledData(T data, List<Interval> intervals) {
		super(intervals);
		this.data = data;
	}
	public T getData() {
		return data;
	}
}
