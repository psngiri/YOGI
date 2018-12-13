package yogi.report.condition.date;

import yogi.period.date.range.io.DateRangeFormatter;

public class DefaultDateRangeFormatter extends DateRangeFormatter {

	private static final long serialVersionUID = 4587336911643982606L;

	public DefaultDateRangeFormatter() {
		super(new DateFormatter(), '-');
	}

}
