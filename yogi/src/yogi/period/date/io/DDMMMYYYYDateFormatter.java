package yogi.period.date.io;

import java.io.Serializable;
import java.util.Calendar;

import yogi.base.io.Formatter;
import yogi.period.date.Date;
import yogi.period.date.DateAssistant;
import yogi.period.date.DateManager;
import yogi.period.date.DateUtil;

public class DDMMMYYYYDateFormatter implements Formatter<Date>, Serializable{
		
	
	private static final long serialVersionUID = -3749819122448785743L;

	public String format(Date date) {
		if(date == DateManager.INFINITY) return "00XXX0000";
		Calendar calendar = DateAssistant.get().getCalendar(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		return String.format("%3$02d%2$3s%1$04d", year, DateUtil.getMonth0Base(month), day);
	}

}
