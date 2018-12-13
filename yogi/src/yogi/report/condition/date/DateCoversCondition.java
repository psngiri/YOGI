package yogi.report.condition.date;

import yogi.base.io.Scanner;
import yogi.base.util.range.Range;
import yogi.period.date.Date;
import yogi.period.date.io.BoundaryNamedDDMMMYYDateScanner;
import yogi.report.condition.BaseRangeInCondition;


public class DateCoversCondition extends BaseRangeInCondition<Date, Range<Date>> {

	private static Scanner<Date, String> scanner = new BoundaryNamedDDMMMYYDateScanner();
	
	public DateCoversCondition(String value) {
		super(value);
	}
	
	@Override
	public Date scan(String token) {
		return scanner.scan(token).create();
	}
	
	@Override
	protected Range<Date> getRange(Date start, Date end) {
		return new Range<Date>(start, true, end, true);
	}
	
}
