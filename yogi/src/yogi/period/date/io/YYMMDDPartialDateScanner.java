package yogi.period.date.io;

import java.util.Calendar;

import yogi.base.Creator;
import yogi.period.date.Date;
import yogi.period.date.DateAssistant;
import yogi.period.date.Dates;
import yogi.period.date.YMDDateCreator;

public class YYMMDDPartialDateScanner extends PartialDateScanner {
	private YMDDateCreator creator = new YMDDateCreator();
	//private DateCreator dateCreator = new DateCreator();
	
	public Creator<Date> scan(String record) {
		int year = 0;
		int month = 0;
		int day = 0;
		boolean flag = false;
		
		if(record.length() < 6) {
			Calendar calendar = DateAssistant.get().getCalendar(getReferenceDate());
			int currentMonth = calendar.get(Calendar.MONTH);
			int currentYear = calendar.get(Calendar.YEAR);
			int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
			currentMonth++;
			
			if(record.length() == 4) {
				month = Integer.parseInt(record.substring(0, 2));
				day = Integer.parseInt(record.substring(2, 4));				
			} else if(record.length() == 3) {
				month = Integer.parseInt(record.substring(0, 1));
				day = Integer.parseInt(record.substring(1, 3));
			} else if(record.length() == 2){
				month = Integer.parseInt(record.substring(0, 2));
				day = 1;
				if(isEndDate()) {
					flag = true;
				}
			} else {
				throw new RuntimeException("INVALID DATE STRING");
			}
			year = currentYear;
			if(month < currentMonth) {
				year = currentYear + 1;
			} else if(month == currentMonth) {
				if(day <= currentDay) {
					year = currentYear + 1;
				}
			}
		} else {
			year = Integer.parseInt(record.substring(0, 2));
			month = Integer.parseInt(record.substring(2, 4));
			day = Integer.parseInt(record.substring(4, 6));
		}		
		creator.setYear(year);
		creator.setMonth(month-1);
		creator.setDay(day);
		if(flag) {
			creator.setMonth(month);			
			Date date = creator.create();
			Date modifiedDate = Dates.subtractDays(date, 1);
			//return dateCreator.setNumberOfDaysSinceEpoch(modifiedDate.getValue());
			Calendar calendar = DateAssistant.get().getCalendar(modifiedDate);
			int modifiedDay = calendar.get(Calendar.DAY_OF_MONTH);
			creator.setDay(modifiedDay);
			creator.setMonth(month-1);
		}
		return creator;
	}
	
}
