package yogi.period.interval.io;

import java.io.Serializable;

import yogi.base.io.Scanner;
import yogi.period.date.range.DateRange;
import yogi.period.date.range.io.DateRangeScanner;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.io.MondayToSundayFrequencyScanner;
import yogi.period.interval.Interval;

public class IntervalScanner implements Serializable{
	
	private static final long serialVersionUID = -7940595042918011450L;
	private DateRangeScanner dateRangeScanner;
	private Scanner<Frequency, String> frequencyScanner;
	private char separator;

	public IntervalScanner() {
		this(new DateRangeScanner(), new MondayToSundayFrequencyScanner(), ' ');
	}

	public IntervalScanner(DateRangeScanner dateRangeScanner, Scanner<Frequency, String> frequencyScanner, char separator) {
		super();
		this.dateRangeScanner = dateRangeScanner;
		this.frequencyScanner = frequencyScanner;
		this.separator = separator;
	}

	public Interval scan(String record)
	{
		int indexOf = record.lastIndexOf(separator);
		if(indexOf < 0) throw new RuntimeException(String.format("Separator %1$s expected for Frequency record: %2$s", separator, record));
		DateRange dateRange = dateRangeScanner.scan(record.substring(0, indexOf));
		Frequency frequency = frequencyScanner.scan(record.substring(indexOf + 1)).create();
		return new Interval(dateRange, frequency);
	}
}
