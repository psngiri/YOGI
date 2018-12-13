package yogi.report.condition.date;

import yogi.period.date.Date;
import yogi.period.date.io.DDMMMYYYYDateScanner;
import yogi.report.condition.BaseInCondition;


public class DateInCondition extends BaseInCondition<Date> {
	private static DDMMMYYYYDateScanner scanner = new DDMMMYYYYDateScanner();
	
	public DateInCondition(String value) {
		super(value);
	}

	public DateInCondition(String value, char separator) {
		super(value, separator);
	}

	@Override
	public Date scan(String value) {
		return scanner.scan(value).create();
	}
	
}