package yogi.period.date.io;

import java.io.Serializable;

import yogi.period.date.Date;
import yogi.period.date.YMDDateCreator;

public class DashDelimitedYYYYMMDDDateScanner implements yogi.base.io.Scanner<Date, String>, Serializable  {
	private static final long serialVersionUID = 1L;
	YMDDateCreator creator = new YMDDateCreator();
	public YMDDateCreator scan(String record) {		
		int year = Integer.parseInt(record.substring(0, 4));
		int month = Integer.parseInt(record.substring(5, 7));
		int day = Integer.parseInt(record.substring(8, 10));
		creator.setYear(year);
		creator.setMonth(month-1);
		creator.setDay(day);
		return creator;
	}

}
