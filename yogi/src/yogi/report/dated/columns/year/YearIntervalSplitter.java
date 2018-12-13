package yogi.report.dated.columns.year;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import yogi.period.date.Date;
import yogi.period.date.DateAssistant;
import yogi.period.date.DateManager;
import yogi.period.date.Dates;
import yogi.period.interval.Interval;
import yogi.report.dated.IntervalSplitter;

public class YearIntervalSplitter implements IntervalSplitter {

	public List<Interval> split(Interval interval) {
		Calendar startCalendar = DateAssistant.get().getCalendar(interval.getDateRange().getStart());
		int startYear = startCalendar.get(Calendar.YEAR);
		Calendar endCalendar = DateAssistant.get().getCalendar(interval.getDateRange().getEnd());
		int endYear = endCalendar.get(Calendar.YEAR);
		List<Interval> rtnValue = new ArrayList<Interval>();
		int currentYear = startYear+1;
		Date currentStartDate = interval.getDateRange().getStart();
		Date currentEndDate = null;
		while(currentYear <= endYear)
		{			
			currentEndDate = Dates.subtractDays(DateManager.get().getDate(currentYear, 0, 1), 1);
			rtnValue.add(new Interval(currentStartDate,	currentEndDate, interval.getFrequency()));

			currentYear ++;
			currentStartDate = Dates.addDays(currentEndDate, 1);
		}
		rtnValue.add(new Interval(currentStartDate,	interval.getDateRange().getEnd(), interval.getFrequency()));
		return rtnValue;
	}

}
