package yogi.report.condition.date;

import java.io.Serializable;

import yogi.base.Creator;
import yogi.period.date.Date;
import yogi.period.date.io.DDMMMYYDateScanner;

public class DateScanner implements yogi.base.io.Scanner<Date, String>, Serializable {

	private static final long serialVersionUID = 923453098957811259L;
	private static DDMMMYYDateScanner dateScanner = new DDMMMYYDateScanner();	

	@Override
	public Creator<Date> scan(String record) {
		return dateScanner.scan(record);
	}

}
