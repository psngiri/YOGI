package yogi.period.date.io;

import yogi.base.Creator;
import yogi.period.date.Date;
import yogi.period.date.InfinityDateCreator;
import yogi.period.date.YMMMDDateCreator;

public class DashDelimitedDDMMMYYDateScanner implements yogi.base.io.Scanner<Date, String> {
	
	public Creator<Date> scan(String record) {
		if(record.equals("00-XXX-0000")) return InfinityDateCreator.creator;
		YMMMDDateCreator creator =  new YMMMDDateCreator();
		
		int day = Integer.parseInt(record.substring(0, 2));
		String month = record.substring(3, 6);
		int year = Integer.parseInt(record.substring(7, 9));
		creator.setYear(year);
		creator.setMonth(month);
		creator.setDay(day);
		return creator;
	}

}
