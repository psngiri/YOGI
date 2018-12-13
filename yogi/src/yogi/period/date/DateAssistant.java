package yogi.period.date;

import java.util.Calendar;
import java.util.TimeZone;

import yogi.base.Assistant;
import yogi.period.date.range.DateRange;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyManager;

public class DateAssistant extends Assistant<Date>{
	private static DateAssistant dateAssistant = new DateAssistant();
	
	public static DateAssistant get() {
		return dateAssistant;
	}
	/* 
	 * This method uses the GMT Calendar
	 */
	public Calendar getCalendar(Date date)
	{
		Calendar calendar = getGMTCalendar();
		return getCalendar(date, calendar);
	}
	
	public Date getDate(java.util.Date date)
	{
		return DateManager.get().getDate(date.getTime()/DateManager.MillisInADay);
	}
	
	public Date getCurrentDate()
	{
		return DateManager.get().getDate(System.currentTimeMillis()/DateManager.MillisInADay);
	}	
	
	public Calendar getGMTCalendar() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		return calendar;
	}

	public Calendar getCalendar(Date date, Calendar calendar) {
		return getCalendar(calendar, date.getValue());
	}
	
	public Calendar getCalendar(Calendar calendar, long daysSinceEpoc) {
		calendar.clear();
		calendar.setTimeInMillis(daysSinceEpoc*DateManager.MillisInADay);
		return calendar;
	}
	
	public int getDayOfWeek(Date date) {
		Calendar calendar = DateAssistant.get().getCalendar(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	}
	
	public int getDayOfWeekMondayToSunday(Date date)
	{
		int sundayToSaturdayDow = getDayOfWeek(date);
		if(sundayToSaturdayDow == 1) return 7;
		return sundayToSaturdayDow -1;
	}
	
	public Date adjustDateForward(Date date, Frequency frequency) {
		int j = findMatchForward(date, frequency);
		return Dates.addDays(date, j);
	}
	
	private int findMatchForward(Date date, Frequency frequency) {
		int dayOfWeek = getDayOfWeek(date);
		int mask = FrequencyManager.get().getFrequencyFromDayOfWeek(dayOfWeek).getValue();
		int value = frequency.getValue();
		int j = 0;
		for(int i = dayOfWeek-1; i < 7; i++)
		{
			if((value & mask) != 0 ) return j;
			mask = mask >> 1;
			j = j+1;
		}
		mask = Frequency.SUNDAY;
		for(int i = 0; i < dayOfWeek; i ++)
		{
			if((value & mask) != 0 ) return j;
			mask = mask >> 1;
			j = j+1;
		}
		return j;
	}
	
	private int findMatchBackward(Date date, Frequency frequency) {
		int dayOfWeek = getDayOfWeek(date);
		int mask = FrequencyManager.get().getFrequencyFromDayOfWeek(dayOfWeek).getValue();
		int value = frequency.getValue();
		int j = 0;
		for(int i = dayOfWeek-1; i >=0; i--)
		{
			if((value & mask) != 0 ) return j;
			mask = mask << 1;
			j = j+1;
		}
		mask = Frequency.SATURDAY;
		for(int i = 7; i >= dayOfWeek; i --)
		{
			if((value & mask) != 0 ) return j;
			mask = mask << 1;
			j = j+1;
		}
		return j;
	}
	
	public Date adjustDateBackward(Date date, Frequency frequency) {
		int j = findMatchBackward(date, frequency);
		return Dates.subtractDays(date, j);
	}
	
	public Frequency adjustFrequency(DateRange dateRange, Frequency frequency) {
		long numberOfDays = dateRange.getNumberOfDays();
		if(numberOfDays > 6) return frequency;
		int dayOfWeek = getDayOfWeek(dateRange.getStart());
		int mask = FrequencyManager.get().getFrequencyFromDayOfWeek(dayOfWeek).getValue();
		int value = mask;
		for(int i = 0; i < numberOfDays-1; i++)
		{
			mask = mask >> 1;
			value = value | mask;
		}
		return FrequencyManager.get().getFrequency(value & frequency.getValue());
	}
	
}
