package yogi.report.condition.date;

import java.util.ArrayList;
import java.util.List;

import yogi.period.date.io.DDMMMYYDateScanner;
import yogi.period.date.range.DateRange;
import yogi.period.date.range.DateRanges;
import yogi.period.date.range.io.DateRangeScanner;
import yogi.report.condition.ConditionBaseImpl;


public class DateRangeNotInCondition extends ConditionBaseImpl<DateRange> {
	private static DateRangeScanner scanner = new DateRangeScanner(new DDMMMYYDateScanner(), '-');
	private List<DateRange> ranges;
	public DateRangeNotInCondition(String value) {
		super(value);
		String[] tokens = value.split(",");
		ranges = new ArrayList<DateRange>(tokens.length);
		for(String token: tokens)
		{
			ranges.add(scanner.scan(token));
		}
	}
	
	
	public boolean satisfied(DateRange data) {
		List<DateRange> dateRanges = DateRanges.intersection(ranges, data);
		return !DateRanges.subtract(data, dateRanges).isEmpty();
	}

}
