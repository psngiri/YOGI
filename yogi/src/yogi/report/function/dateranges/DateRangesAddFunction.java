package yogi.report.function.dateranges;

import java.util.ArrayList;
import java.util.List;

import yogi.period.date.range.DateRange;
import yogi.period.date.range.DateRanges;
import yogi.report.function.Function;

public class DateRangesAddFunction implements Function<List<DateRange>>{
	List<DateRange> dateRanges = new ArrayList<DateRange>();
	
	public void reset() {
		dateRanges = new ArrayList<DateRange>();
	}

	public void process(List<DateRange> object, int multiplier) {
		if(object == null) return;
		dateRanges = DateRanges.add(dateRanges, object);
	}

	public List<DateRange> getValue() {
		return dateRanges;
	}

}
