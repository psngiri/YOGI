package yogi.report.dated.columns.week.mondaytosunday;

import yogi.period.date.Date;
import yogi.period.date.DateAssistant;
import yogi.report.dated.columns.week.WeekSplitter;

public class MondayToSundayWeekSplitter extends WeekSplitter {

	@Override
	public int getDayOfWeek(Date date) {
		return DateAssistant.get().getDayOfWeekMondayToSunday(date);
	}

}
