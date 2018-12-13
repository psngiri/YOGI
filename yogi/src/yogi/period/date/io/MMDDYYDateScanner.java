package yogi.period.date.io;

import yogi.period.date.Date;
import yogi.period.date.YMDDateCreator;

public class MMDDYYDateScanner implements yogi.base.io.Scanner<Date, String> {
	YMDDateCreator creator = new YMDDateCreator();
	public YMDDateCreator scan(String record) {
		int month = Integer.parseInt(record.substring(0, 2));
		int day = Integer.parseInt(record.substring(2, 4));
		int year = Integer.parseInt(record.substring(4, 6));
		creator.setYear(year);
		creator.setMonth(month-1);
		creator.setDay(day);
		return creator;
	}

}
