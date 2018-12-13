package yogi.report.condition.date;


import yogi.period.date.Date;
import yogi.period.date.io.DDMMMYYYYDateScanner;
import yogi.report.condition.BaseGreaterThanOrEqualsCondition;


public class DateGreaterThanOrEqualsCondition extends BaseGreaterThanOrEqualsCondition<Date> {
	
	private static DDMMMYYYYDateScanner scanner = new DDMMMYYYYDateScanner();
	
	public DateGreaterThanOrEqualsCondition(String value) {
		super(value);
	}

	@Override
	public Date scan(String value) {
		return scanner.scan(value).create();
	}

}
