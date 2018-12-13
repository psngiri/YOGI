package yogi.period.date.vector;

import java.util.ArrayList;

import yogi.period.date.Date;
import yogi.period.date.range.DateRange;

public class DateVector <T> {
	private DateRange dateRange;
	private ArrayList<T> vector;
	
	public DateVector(DateRange dateRange) {
		super();
		this.dateRange = dateRange;
		vector = new ArrayList<T>(getIndex(dateRange.getEnd()));
	}

	private int getIndex(Date date) {
		Date startDate = dateRange.getStart();
		long index = date.getValue() - startDate.getValue();
		if(index > Integer.MAX_VALUE) throw new RuntimeException("DateVector cannot be larger than " + Integer.MAX_VALUE + " start date :" + startDate + " date :" +date);
		return (int) index;
	}
	
	public void set(Date date, T object)
	{
		int index = getIndex(date);
		vector.set(index, object);
	}
	
	public DateRange getDateRange() {
		return dateRange;
	}

	public T get(Date date)
	{
		int index = getIndex(date);
		return vector.get(index);
	}
}
