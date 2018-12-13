package yogi.period.date.io;

import java.util.Calendar;

import yogi.base.Creator;
import yogi.period.date.Date;
import yogi.period.date.DateAssistant;
import yogi.period.date.DateUtil;
import yogi.period.date.YMMMDDateCreator;

public class YYMMMDDPartialDateScanner extends PartialDateScanner {
	private YMMMDDateCreator creator = new YMMMDDateCreator();
	
	public Creator<Date> scan(String record) {
		int year = 0;
		int month = 0;
		String monthStr = "";
		int day = 0;
		 
		if(record.length() < 7) {
			Calendar calendar = DateAssistant.get().getCalendar(getReferenceDate());
			int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
			int currentMonth = calendar.get(Calendar.MONTH);
			int currentYear = calendar.get(Calendar.YEAR);
			
			if(record.length() == 5) {
				day = Integer.parseInt(record.substring(0, 2));
				monthStr = record.substring(2, 5);
				month = DateUtil.getMonthIndex(monthStr);
			} else if(record.length() == 2) {
				day = Integer.parseInt(record.substring(0, 2));
				if(isEndDate()) {
					if(day < currentDay) {
						month = currentMonth + 1;
					} else {
						month = currentMonth;
					}
				} else {
					month = currentMonth;
				}
			} else {
				throw new RuntimeException("INVALID DATE STRING");
			}
			if(isEndDate()) {
				if(month > currentMonth) {
					year = currentYear;
				} else if(month < currentMonth){
					year = currentYear + 1;
				} else {
					if(day < currentDay) {
						year = currentYear + 1;
					} else {
						year = currentYear;
					}
				}				
			} else {
				year = currentYear;
			}		
		} else {
			day = Integer.parseInt(record.substring(0, 2));
			monthStr = record.substring(2, 5);
			month = DateUtil.getMonthIndex(monthStr);
			year = Integer.parseInt(record.substring(5, 7));
		}
		creator.setYear(year);
		creator.setMonth(month);
		creator.setDay(day);		
		return creator;
	}

}
