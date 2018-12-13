package yogi.period.date.io;

import java.sql.Date;

public class DateDbScanner{
	
	public static Long scan(String record) {
			record=record.trim();
			String day;
			String month;
			String year;
			String[] yearDayMonth;
			String split = "/";
			if(record.contains("-"))
				split = "-";
			yearDayMonth = record.split(split);
			day = yearDayMonth[0];
			if(day.length()==1)
				day = "0" + day;
			month = yearDayMonth[1];
			year = yearDayMonth[2];
			switch(month.toUpperCase()){
				case "JAN":month="01";break;
				case "FEB":month="02";break;
				case "MAR":month="03";break;
				case "APR":month="04";break;
				case "APL":month="04";break;
				case "MAY":month="05";break;
				case "JUN":month="06";break;
				case "JUL":month="07";break;
				case "AUG":month="08";break;
				case "SEP":month="09";break;
				case "OCT":month="10";break;
				case "NOV":month="11";break;
				case "DEC":month="12";break;
				default:break;
			}
			if(month.length()==1)
				month = "0" + month;
			Date date;
			date = Date.valueOf(year+"-"+month+"-"+day);
			return date.getTime();
	}

}
