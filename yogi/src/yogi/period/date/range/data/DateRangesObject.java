package yogi.period.date.range.data;

import java.util.ArrayList;
import java.util.List;

import yogi.base.util.immutable.ImmutableList;
import yogi.period.date.range.DateRange;
import yogi.period.date.range.DateRanges;

public class DateRangesObject {
	private List<DateRange> dateRanges;
	protected DateRangesObject() {
		this(new ArrayList<DateRange>());
	}
	protected DateRangesObject(List<DateRange> dateRanges) {
		super();
		this.dateRanges = new ArrayList<DateRange>(dateRanges);
	}

	public ImmutableList<DateRange> getDateRanges() {
		return new ImmutableList<DateRange>(dateRanges);
	}
	
	protected void add(DateRange dateRange)
	{
		List<DateRange> newDateRanges = DateRanges.subtract(dateRange, dateRanges);
		dateRanges.addAll(newDateRanges);
	}
	
	protected void add(List<DateRange> dateRanges)
	{
		setDateRanges(DateRanges.add(this.dateRanges, dateRanges));
	}
	
	protected void compress() {
		dateRanges = DateRanges.compress(dateRanges);
	}

	protected void delete(DateRange dateRange)
	{
		setDateRanges(DateRanges.subtract(dateRanges, dateRange));
	}
	protected void delete(List<DateRange> dateRanges)
	{
		setDateRanges(DateRanges.subtract(this.dateRanges, dateRanges));
	}
	
	public boolean isEmpty()
	{
		return dateRanges.isEmpty();
	}
	
	private void setDateRanges(List<DateRange> dateRanges)
	{
		this.dateRanges.clear();
		this.dateRanges.addAll(dateRanges);
	}
}
