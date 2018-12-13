package yogi.report.condition;

import java.util.List;

import yogi.period.date.io.YYMMMDDPartialDateScanner;
import yogi.period.date.range.DateRange;
import yogi.period.date.range.io.PartialDateRangesScanner;

public class DateRangesListInCondition extends BaseInCondition<List<DateRange>> {

	private YYMMMDDPartialDateScanner dateScanner = null;
	private PartialDateRangesScanner dateRangesScanner = null;

	public DateRangesListInCondition(String value) {
		super(value);
	}
	
	@Override
	public List<DateRange> scan(String token) {
		dateScanner = new YYMMMDDPartialDateScanner();
		dateRangesScanner = new PartialDateRangesScanner(dateScanner);
		return dateRangesScanner.scan(token);		
	}
	
}
