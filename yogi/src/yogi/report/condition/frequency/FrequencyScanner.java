package yogi.report.condition.frequency;

import java.io.Serializable;

import yogi.base.Creator;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.io.MondayToSundayOptionalOffCharFrequencyScanner;

public class FrequencyScanner implements yogi.base.io.Scanner<Frequency, String>, Serializable{

	private static final long serialVersionUID = 4526217964494910503L;
	private static MondayToSundayOptionalOffCharFrequencyScanner scanner = new MondayToSundayOptionalOffCharFrequencyScanner(new char[]{'M', 'T', 'W', 'Q', 'F', 'J', 'S'});
	
	public Creator<Frequency> scan(String value) {
		return scanner.scan(value);
	}
}
