package yogi.period.date.range.io;

import yogi.period.date.Date;
import yogi.period.date.io.PartialDateScanner;


public class PartialDateRangesScanner extends DateRangesScanner {

	private PartialDateScanner dateScanner;
	
	public PartialDateRangesScanner(PartialDateScanner dateScanner, char rangeSeparator, String listSeparator, String allDateStr) {
		super(dateScanner, rangeSeparator, listSeparator, allDateStr);
		this.dateScanner = dateScanner;
	}

	public PartialDateRangesScanner(PartialDateScanner dateScanner) {
		super(dateScanner);
		this.dateScanner = dateScanner;
	}

	protected Date getEndDate(String endDate) {
		dateScanner.setEndDate(true);
		return dateScanner.scan(endDate).create();
	}

	protected Date getStartDate(String startDate) {
		dateScanner.setEndDate(true);
		return dateScanner.scan(startDate).create();
	}
	
}
