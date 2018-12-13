package yogi.report.dated.columns.month;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import yogi.period.date.Date;
import yogi.period.date.DateAssistant;
import yogi.period.date.DateManager;
import yogi.period.date.Dates;
import yogi.period.interval.Interval;
import yogi.report.dated.IntervalSplitter;

public class MonthIntervalSplitter implements IntervalSplitter {

	public List<Interval> split(Interval interval) {
		Calendar startCalendar = DateAssistant.get().getCalendar(interval.getDateRange().getStart());
		int startYear = startCalendar.get(Calendar.YEAR);
		int startMonth = startCalendar.get(Calendar.MONTH);
		Calendar endCalendar = DateAssistant.get().getCalendar(interval.getDateRange().getEnd());
		int endYear = endCalendar.get(Calendar.YEAR);
		int endMonth = endCalendar.get(Calendar.MONTH);
		List<Interval> rtnValue = new ArrayList<Interval>();
		int currentYear = startYear;
		int currentMonth = startMonth +1;
		Date currentStartDate = interval.getDateRange().getStart();
		Date currentEndDate = null;
		while((currentYear < endYear) || (currentMonth <= endMonth))
		{
			if(currentMonth == 12)
			{
				currentMonth = 0;
				currentYear ++;
			}
			
			currentEndDate = Dates.subtractDays(DateManager.get().getDate(currentYear, currentMonth, 1), 1);
			rtnValue.add(new Interval(currentStartDate,	currentEndDate, interval.getFrequency()));

			currentMonth ++;
			currentStartDate = Dates.addDays(currentEndDate, 1);
		}
		rtnValue.add(new Interval(currentStartDate,	interval.getDateRange().getEnd(), interval.getFrequency()));
		return rtnValue;
	}

}
