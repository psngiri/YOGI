package yogi.period.date;

import java.util.Calendar;

import yogi.base.ObjectNotFoundException;
import yogi.base.relationship.index.IndexedManager;

public class DateManager extends IndexedManager<Date, Long> {
	private static DateManager dateManager = new DateManager();
	public static final int MillisInADay = 60*60*24*1000;
	public static final Date INFINITY = new  DateImpl(Long.MAX_VALUE);
	public static final Date EARLIEST_DATE = new DateImpl(0);
	public static final Date UNKNOWN_DATE = new DateImpl(-1);
	private DateCreator dateCreator = new DateCreator();
	
	public static DateManager get() {
		return dateManager;
	}

	protected DateManager() {
		super();
	}

	
	public Date getDate(long numberOfDaysSinceEpoch)
	{
		if(numberOfDaysSinceEpoch == Long.MAX_VALUE) return DateManager.INFINITY;
		if(numberOfDaysSinceEpoch == 0) return DateManager.EARLIEST_DATE;
		if(numberOfDaysSinceEpoch == -1) return DateManager.UNKNOWN_DATE;
		try {
			return getObject(numberOfDaysSinceEpoch);
		} catch (ObjectNotFoundException e) {
			return createDate(numberOfDaysSinceEpoch);
		}
	}

	private synchronized Date createDate(long numberOfDaysSinceEpoch) {
		try {
			return getObject(numberOfDaysSinceEpoch);
		} catch (ObjectNotFoundException e) {
			return DateFactory.get().create(dateCreator.setNumberOfDaysSinceEpoch(numberOfDaysSinceEpoch));
		}
	}
	
	/* here month is similar to Calendar Month which goes from 0 - 11.  0 for January and 11 for December*/
	public Date getDate(int year, int month, int day)
	{
		Calendar calendar = DateAssistant.get().getGMTCalendar();
		calendar.clear();
		year = convert2DigitYearTo4Digit(year);
		calendar.set(year, month, day);
		long timeInMillis = calendar.getTimeInMillis();
		long numberOfDaysSinceEpoch = timeInMillis/MillisInADay;
		return getDate(numberOfDaysSinceEpoch);
	}
	
	private static int convert2DigitYearTo4Digit(int year)
	{
		if(year > 99) return year;
		if(year > 70) year += 1900;
		else year = year += 2000;
		return year;
	}
	
}
