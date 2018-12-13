package yogi.period.date.io;

import java.io.Serializable;

import yogi.base.Creator;
import yogi.period.date.Date;
import yogi.period.date.InfinityDateCreator;
import yogi.period.date.YMMMDDateCreator;

public class DDMMMYYDateScanner implements yogi.base.io.Scanner<Date, String>, Serializable {
	private static final long serialVersionUID = 8333728343110290371L;
	private YMMMDDateCreator creator = new YMMMDDateCreator();
	
	public Creator<Date> scan(String record) {
		if(record.equals("00XXX00")) return InfinityDateCreator.creator;
		if(record.length()==6 && record.charAt(0)!='0')
			record = "0" + record;
		setDayAndMonth(record);
		int year = Integer.parseInt(record.substring(5, 7));
		creator.setYear(year);
		return creator;
	}

	public YMMMDDateCreator setDayAndMonth(String record) {
		int day = Integer.parseInt(record.substring(0, 2));
		creator.setMonth(record.substring(2, 5));
		creator.setDay(day);
		return creator;
	}
}
