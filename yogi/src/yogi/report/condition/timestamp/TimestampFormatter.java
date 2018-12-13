package yogi.report.condition.timestamp;

import java.io.Serializable;
import java.util.Calendar;

import yogi.base.io.Formatter;
import yogi.period.date.DateAssistant;

public class TimestampFormatter implements Formatter<Long>, Serializable {

	private static final long serialVersionUID = 1L;

	public String format(Long timeInMillis) {
		long timeInSeconds = timeInMillis/1000;
		long second = timeInSeconds%60;
		long minute = (timeInSeconds/60)%60;
		long hour = (timeInSeconds/3600)%24;
		Calendar calendar = DateAssistant.get().getGMTCalendar();
		calendar.setTimeInMillis(timeInMillis);
		int year = calendar.get(1);
		int month = calendar.get(2)+1;
        int day = calendar.get(5);
    	String monthString;  
		switch(month){
			 case 1:monthString="JAN";break;
			 case 2:monthString="FEB";break;
			 case 3:monthString="MAR";break;
			 case 4:monthString="APR";break;
			 case 5:monthString="MAY";break;
			 case 6:monthString="JUN";break;
			 case 7:monthString="JUL";break;
			 case 8:monthString="AUG";break;
			 case 9:monthString="SEP";break;
			case 10:monthString="OCT";break;
			case 11:monthString="NOV";break;
			case 12:monthString="DEC";break;
			default:throw new IllegalArgumentException();
		}
		String date = String.format("%02d/%s/%04d", day,monthString,year);
		String time = String.format("%02d:%02d:%02d", hour,minute,second);
		return date + " " + time;
		
	}
}
