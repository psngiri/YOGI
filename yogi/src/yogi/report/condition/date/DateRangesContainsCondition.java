package yogi.report.condition.date;

import java.util.List;

import yogi.period.date.io.YYMMMDDPartialDateScanner;
import yogi.period.date.range.DateRange;
import yogi.period.date.range.DateRanges;
import yogi.period.date.range.io.PartialDateRangesScanner;
import yogi.report.condition.ConditionBaseImpl;


public class DateRangesContainsCondition extends ConditionBaseImpl<List<DateRange>> {
	
	private List<DateRange> ranges;
	private YYMMMDDPartialDateScanner dateScanner = new YYMMMDDPartialDateScanner();
	private PartialDateRangesScanner dateRangesScanner = new PartialDateRangesScanner(dateScanner);
	
	public DateRangesContainsCondition(String value) {
		super(value);
		ranges = dateRangesScanner.scan(value);
	}
		
	public boolean satisfied(List<DateRange> data) {
		return DateRanges.contains(data, ranges);
	}

}
