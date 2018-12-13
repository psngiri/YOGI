package yogi.report.dated.columns.dow;

import yogi.base.io.Formatter;
import yogi.period.date.DateUtil;
import yogi.period.frequency.Frequency;
import yogi.period.interval.Interval;
import yogi.report.dated.DatedColumnDefinitionImpl;


public class DayOfWeekColumn<T> extends DatedColumnDefinitionImpl<T, Integer> {

	public DayOfWeekColumn()
	{
		this("DayOfWeek", 9, new MyFormatter(), null);
	}
	
	public DayOfWeekColumn(String name, int width, Formatter<Integer> formatter, String[] heading) {
		super(name, width, formatter, heading, new DayOfWeekSplitter());
	}

	public Integer getValue(Interval interval) {
		byte myFrequency = interval.getFrequency().getValue();
		int mask = Frequency.SUNDAY;
		for(int i = 0; i < 7; i ++)
		{
			if((myFrequency & mask) != 0 ) return i+1; 
			mask = mask >> 1;
		}
		throw new RuntimeException("Could not get Day Of Week from interval: " + interval);
	}

	static class MyFormatter implements Formatter<Integer>
	{
		public String format(Integer object) {
			if(object == null) return "";
			return DateUtil.getDayOfWeek(object);
		}
	}
}
