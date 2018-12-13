package yogi.period.date.range.io;

import java.io.Serializable;

import yogi.base.io.Scanner;
import yogi.period.date.Date;
import yogi.period.date.io.DDMMMYYYYDateScanner;
import yogi.period.date.range.DateRange;

public class DateRangeScanner implements Serializable{
	
	private static final long serialVersionUID = 4102574600275931165L;
	private Scanner<Date, String> dateScanner;
	private char separator;
	
	
	public DateRangeScanner() {
		this(new DDMMMYYYYDateScanner(), ' ');
	}


	public DateRangeScanner(Scanner<Date, String> dateScanner, char separator) {
		super();
		this.dateScanner = dateScanner;
		this.separator = separator;
	}


	public DateRange scan(String record)
	{
		int indexOf = record.indexOf(separator);
		if(indexOf < 0) throw new RuntimeException(String.format("Separator %1$s expected for date range record: %2$s", separator, record));
		Date startDate = dateScanner.scan(record.substring(0, indexOf)).create();
		Date endDate = dateScanner.scan(record.substring(indexOf + 1)).create();
		return new DateRange(startDate, endDate);
	}
}
