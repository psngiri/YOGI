package yogi.period.date.io;

import java.util.Calendar;

import yogi.base.io.Formatter;
import yogi.period.date.Date;
import yogi.period.date.DateAssistant;
import yogi.period.date.DateManager;
import yogi.period.date.DateUtil;

public class BoundaryNamedDDMMMYYDateFormatter implements Formatter<Date>{

	private String earliestDateStr;
	private String infinityDateStr;
	
	public BoundaryNamedDDMMMYYDateFormatter(String earliestDate, String infinityDate) {
		this.earliestDateStr = earliestDate;
		this.infinityDateStr = infinityDate;
	}

	public BoundaryNamedDDMMMYYDateFormatter() {
		this("BLANK", "BLANK");
	}

	public String format(Date date) {
		if(date == DateManager.INFINITY) return infinityDateStr;
		if(date == DateManager.EARLIEST_DATE) return earliestDateStr;
		Calendar calendar = DateAssistant.get().getCalendar(date);
		int year = calendar.get(Calendar.YEAR)%100;
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		return String.format("%3$02d%2$3s%1$02d", year, DateUtil.getMonth0Base(month), day);
	}
}
