package yogi.report.dated.columns.week.sundaytosaturday;

import yogi.period.date.Date;
import yogi.period.date.DateAssistant;
import yogi.report.dated.columns.week.WeekSplitter;

public class SundayToSaturdayWeekSplitter extends WeekSplitter {

	@Override
	public int getDayOfWeek(Date date) {
		return DateAssistant.get().getDayOfWeek(date);
	}

}
