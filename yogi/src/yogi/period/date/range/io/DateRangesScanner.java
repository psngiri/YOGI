package yogi.period.date.range.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yogi.base.io.Scanner;
import yogi.period.date.Date;
import yogi.period.date.Dates;
import yogi.period.date.range.DateRange;
import yogi.period.date.range.DateRanges;

public class DateRangesScanner {
	private Scanner<Date, String> dateScanner;
	private char rangeSeparator;
	private String listSeparator;
	private String allDateStr;

	public DateRangesScanner(Scanner<Date, String> dateScanner) {
		this(dateScanner, '-', "\\^", "*");
	}

	public DateRangesScanner(Scanner<Date, String> dateScanner, char rangeSeparator, String listSeparator, String allDateStr) {
		super();
		this.dateScanner = dateScanner;
		this.rangeSeparator = rangeSeparator;
		this.listSeparator = listSeparator;
		this.allDateStr = allDateStr;
	}

	private List<DateRange> parse(String record) {
		String[] splits = record.split(listSeparator);
		List<DateRange> dateRangeList = new ArrayList<DateRange>();
		List<String> strDateRangeList = Arrays.asList(splits);
		for(String strDateRange: strDateRangeList) {
			strDateRange = strDateRange.trim();
			if(strDateRange.equals(allDateStr)) {
				dateRangeList.add(DateRange.ALL_DATE);
			} else {
				int indexOf = strDateRange.indexOf(rangeSeparator);
				if(indexOf < 0) throw new RuntimeException(String.format("Separator %1$s expected for date range record: %2$s", rangeSeparator, record));
				Date startDate = getStartDate(strDateRange.substring(0, indexOf));
				Date endDate = getEndDate(strDateRange.substring(indexOf + 1));
				if(startDate.compareTo(endDate) > 0) {
					endDate = Dates.addYears(endDate, 1);
				}
				dateRangeList.add(new DateRange(startDate, endDate));
			}
		}
		return dateRangeList;
	}

	protected Date getEndDate(String endDate) {
		return dateScanner.scan(endDate).create();
	}

	protected Date getStartDate(String startDate) {
		return dateScanner.scan(startDate).create();
	}
	
	public List<DateRange> scan(String value) {
		List<DateRange> ranges = null;
		try {
			value = value.trim();
			if(value.isEmpty()) {
				return DateRanges.EMPTY_IMMUTABLE_LIST;
			}
			if(allDateStr.equals(value)) {
				return DateRanges.ALL_DATE_LIST;		
			}
			ranges = parse(value);
		} catch(Exception e) {
			throw new RuntimeException("DateRanges input should be in DDMMMYY-DDMMMYY or DDMMM-DDMMM format seperated by " + listSeparator.charAt(listSeparator.length()-1) + " : " + value);
		}
		ranges = DateRanges.compress(ranges);
		return ranges;
	}
}
