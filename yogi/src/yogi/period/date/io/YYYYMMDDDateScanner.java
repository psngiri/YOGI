package yogi.period.date.io;

import yogi.period.date.Date;
import yogi.period.date.YMDDateCreator;

public class YYYYMMDDDateScanner implements yogi.base.io.Scanner<Date, String> {
	YMDDateCreator creator = new YMDDateCreator();
	public YMDDateCreator scan(String record) {
		int year = Integer.parseInt(record.substring(0, 4));
		int month = Integer.parseInt(record.substring(4, 6));
		int day = Integer.parseInt(record.substring(6, 8));
		creator.setYear(year);
		creator.setMonth(month-1);
		creator.setDay(day);
		return creator;
	}

}
