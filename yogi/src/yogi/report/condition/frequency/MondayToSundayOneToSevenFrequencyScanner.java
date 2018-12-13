package yogi.report.condition.frequency;

import java.io.Serializable;

import yogi.base.Creator;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.io.MondayToSundayOptionalOffCharFrequencyScanner;

public class MondayToSundayOneToSevenFrequencyScanner implements yogi.base.io.Scanner<Frequency, String>, Serializable{

	private static final long serialVersionUID = 2439713317151586224L;
	private static MondayToSundayOptionalOffCharFrequencyScanner scanner = new MondayToSundayOptionalOffCharFrequencyScanner(new char[]{'1', '2', '3', '4', '5', '6', '7'});
	
	public Creator<Frequency> scan(String value) {
		return scanner.scan(value);
	}
}
