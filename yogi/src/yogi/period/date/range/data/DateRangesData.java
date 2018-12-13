package yogi.period.date.range.data;

import java.util.ArrayList;
import java.util.List;

import yogi.period.date.range.DateRange;

public class DateRangesData<T> extends DateRangesObject{
	private T data;
	public DateRangesData(T data) {
		this(data, new ArrayList<DateRange>());
	}
	public DateRangesData(T data, List<DateRange> dateRanges) {
		super(dateRanges);
		this.data = data;
	}
	public T getData() {
		return data;
	}
}
